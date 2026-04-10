package com.souschef.ui.screens.recipe.cooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.souschef.model.recipe.RecipeStep
import com.souschef.model.recipe.ResolvedIngredient
import com.souschef.repository.ingredient.IngredientRepository
import com.souschef.repository.recipe.RecipeRepository
import com.souschef.usecases.recipe.CookingSessionUseCase
import com.souschef.usecases.recipe.RecipeCalculationUseCase
import com.souschef.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for the step-by-step cooking mode screen.
 *
 * Receives nav params (recipeId + flavour adjustments), loads steps & ingredients,
 * then delegates step navigation / timer to [CookingSessionUseCase].
 */
class CookingModeViewModel(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository,
    private val calculationUseCase: RecipeCalculationUseCase,
    private val recipeId: String,
    private val selectedServings: Int,
    private val spiceLevel: Float,
    private val saltLevel: Float,
    private val sweetnessLevel: Float
) : ViewModel() {

    // ── Internal mutable state ──────────────────────────────

    private val _isLoading = MutableStateFlow(true)
    private val _error = MutableStateFlow<String?>(null)
    private val _steps = MutableStateFlow<List<RecipeStep>>(emptyList())
    private val _adjustedIngredients = MutableStateFlow<List<ResolvedIngredient>>(emptyList())
    private val _isFinished = MutableStateFlow(false)

    // Session state proxied through MutableStateFlows so combine works reactively
    private val _currentStepIndex = MutableStateFlow(0)
    private val _timerMillisRemaining = MutableStateFlow(0L)
    private val _isTimerRunning = MutableStateFlow(false)
    private val _timerFinished = MutableStateFlow(false)

    private var session: CookingSessionUseCase? = null

    // ── Public UiState ──────────────────────────────────────

    val uiState: StateFlow<CookingModeUiState> = combine(
        _isLoading,
        _error,
        _steps,
        _adjustedIngredients,
        _isFinished
    ) { isLoading, error, steps, ingredients, isFinished ->
        CookingModeUiState(
            steps = steps,
            adjustedIngredients = ingredients,
            currentStepIndex = _currentStepIndex.value,
            timerMillisRemaining = _timerMillisRemaining.value,
            isTimerRunning = _isTimerRunning.value,
            timerFinished = _timerFinished.value,
            isLoading = isLoading,
            isFinished = isFinished,
            error = error
        )
    }.combine(
        // Merge in the rapidly-changing timer + step flows
        combine(
            _currentStepIndex,
            _timerMillisRemaining,
            _isTimerRunning,
            _timerFinished
        ) { stepIndex, timer, running, finished ->
            TimerSnapshot(stepIndex, timer, running, finished)
        }
    ) { base, timer ->
        base.copy(
            currentStepIndex = timer.stepIndex,
            timerMillisRemaining = timer.millis,
            isTimerRunning = timer.running,
            timerFinished = timer.finished
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CookingModeUiState()
    )

    init {
        loadData()
    }

    // ── Data Loading ────────────────────────────────────────

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _error.value = null

            // 1. Fetch recipe + steps
            val recipeResult = recipeRepository.getRecipeWithSteps(recipeId)
                .first { it !is Resource.Loading }

            if (recipeResult is Resource.Failure) {
                _isLoading.value = false
                _error.value = recipeResult.message ?: "Failed to load recipe"
                return@launch
            }

            val payload = (recipeResult as? Resource.Success)?.data
            val recipe = payload?.first
            val steps = payload?.second ?: emptyList()

            if (recipe == null || steps.isEmpty()) {
                _isLoading.value = false
                _error.value = "Recipe or steps not found"
                return@launch
            }

            // 2. Resolve ingredients
            val ingredientIds = recipe.ingredients.map { it.globalIngredientId }.distinct()
            var adjusted = emptyList<ResolvedIngredient>()

            if (ingredientIds.isNotEmpty()) {
                val ingredientResult = ingredientRepository.getIngredientsByIds(ingredientIds)
                    .first { it !is Resource.Loading }

                if (ingredientResult is Resource.Success) {
                    val globalMap = ingredientResult.data.associateBy { it.ingredientId }
                    val resolved = recipe.ingredients.mapNotNull { ri ->
                        globalMap[ri.globalIngredientId]?.let { gi ->
                            ResolvedIngredient.from(ri, gi)
                        }
                    }

                    // 3. Apply serving + flavour adjustments
                    adjusted = calculationUseCase.calculate(
                        ingredients = resolved,
                        baseServingSize = recipe.baseServingSize,
                        selectedServings = selectedServings,
                        spiceLevel = spiceLevel,
                        saltLevel = saltLevel,
                        sweetnessLevel = sweetnessLevel
                    )
                }
            }

            // 4. Publish loaded data
            val sortedSteps = steps.sortedBy { it.stepNumber }
            _steps.value = sortedSteps
            _adjustedIngredients.value = adjusted

            // 5. Create session
            val cookingSession = CookingSessionUseCase(sortedSteps, viewModelScope)
            session = cookingSession

            // 6. Bridge session flows → ViewModel flows
            observeSessionFlows(cookingSession)

            _isLoading.value = false
        }
    }

    private fun observeSessionFlows(s: CookingSessionUseCase) {
        viewModelScope.launch {
            s.currentStepIndex.collect { _currentStepIndex.value = it }
        }
        viewModelScope.launch {
            s.timerMillisRemaining.collect { _timerMillisRemaining.value = it }
        }
        viewModelScope.launch {
            s.isTimerRunning.collect { _isTimerRunning.value = it }
        }
        viewModelScope.launch {
            s.timerFinished.collect { _timerFinished.value = it }
        }
    }

    // ── User Actions ────────────────────────────────────────

    fun nextStep() {
        session?.nextStep()
    }

    fun previousStep() {
        session?.previousStep()
    }

    fun goToStep(index: Int) {
        session?.goToStep(index)
    }

    fun startTimer() {
        session?.startTimer()
    }

    fun pauseTimer() {
        session?.pauseTimer()
    }

    fun resetTimer() {
        session?.resetTimer()
    }

    fun clearTimerFinished() {
        session?.clearTimerFinished()
    }

    fun finishCooking() {
        _isFinished.value = true
    }

    // ── Internal helpers ────────────────────────────────────

    private data class TimerSnapshot(
        val stepIndex: Int,
        val millis: Long,
        val running: Boolean,
        val finished: Boolean
    )
}
