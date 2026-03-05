package com.souschef.ui.screens.recipe.create

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.souschef.model.recipe.Ingredient
import com.souschef.ui.components.GhostButton
import com.souschef.ui.components.PrimaryButton
import com.souschef.ui.components.SecondaryButton
import com.souschef.ui.components.SectionHeader
import com.souschef.ui.components.SousChefFilterChip
import com.souschef.ui.components.SousChefTextField
import com.souschef.ui.theme.SousChefTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import java.util.UUID

// ── Predefined tags ─────────────────────────────────────────
val RECIPE_TAGS = listOf(
    "🌿 Vegetarian", "🌱 Vegan", "🌶 Spicy", "⚡ Quick",
    "🇮🇹 Italian", "🇮🇳 Indian", "🇲🇽 Mexican", "🇯🇵 Japanese",
    "🇫🇷 French", "🇹🇭 Thai", "🍰 Dessert", "🥗 Healthy",
    "🍖 BBQ", "🌾 Gluten-Free", "🥜 Nut-Free", "🧀 Dairy-Free"
)

val UNIT_OPTIONS = listOf("grams", "ml", "tsp", "tbsp", "cups", "pieces", "oz", "lbs", "kg", "L")

// ─────────────────────────────────────────────────────────────
// Stateful Screen (wires ViewModel)
// ─────────────────────────────────────────────────────────────

@Composable
fun CreateRecipeScreen(
    onBack: () -> Unit,
    onRecipeSaved: (String) -> Unit,
    viewModel: CreateRecipeViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.generalError) {
        uiState.generalError?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    LaunchedEffect(uiState.isSaved, uiState.savedRecipeId) {
        if (uiState.isSaved && uiState.savedRecipeId != null) {
            onRecipeSaved(uiState.savedRecipeId!!)
        }
    }

    CreateRecipeScreenLayout(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onBack = onBack,
        onNextStep = viewModel::onNextStep,
        onPreviousStep = viewModel::onPreviousStep,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onBaseServingSizeChange = viewModel::onBaseServingSizeChange,
        onUseMinServingChange = viewModel::onUseMinServingChange,
        onMinServingSizeChange = viewModel::onMinServingSizeChange,
        onUseMaxServingChange = viewModel::onUseMaxServingChange,
        onMaxServingSizeChange = viewModel::onMaxServingSizeChange,
        onToggleTag = viewModel::onToggleTag,
        onAddIngredient = viewModel::onAddIngredient,
        onRemoveIngredient = viewModel::onRemoveIngredient,
        onSave = viewModel::onSave
    )
}

// ─────────────────────────────────────────────────────────────
// Stateless Layout
// ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipeScreenLayout(
    uiState: CreateRecipeUiState,
    snackbarHostState: SnackbarHostState,
    onBack: () -> Unit,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBaseServingSizeChange: (Int) -> Unit,
    onUseMinServingChange: (Boolean) -> Unit,
    onMinServingSizeChange: (Int) -> Unit,
    onUseMaxServingChange: (Boolean) -> Unit,
    onMaxServingSizeChange: (Int) -> Unit,
    onToggleTag: (String) -> Unit,
    onAddIngredient: (Ingredient) -> Unit,
    onRemoveIngredient: (String) -> Unit,
    onSave: (Boolean) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Create Recipe", style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = if (uiState.canGoBack) onPreviousStep else onBack) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Step indicator
            StepIndicator(
                currentStep = uiState.currentStep,
                labels = uiState.stepLabels,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Content — animated transition between steps
            AnimatedContent(
                targetState = uiState.currentStep,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
                    } else {
                        slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
                    }
                },
                label = "wizard_step",
                modifier = Modifier.weight(1f)
            ) { step ->
                when (step) {
                    0 -> Step1Details(
                        uiState = uiState,
                        onTitleChange = onTitleChange,
                        onDescriptionChange = onDescriptionChange,
                        onBaseServingSizeChange = onBaseServingSizeChange,
                        onUseMinServingChange = onUseMinServingChange,
                        onMinServingSizeChange = onMinServingSizeChange,
                        onUseMaxServingChange = onUseMaxServingChange,
                        onMaxServingSizeChange = onMaxServingSizeChange,
                        onToggleTag = onToggleTag
                    )
                    1 -> Step2Ingredients(
                        ingredients = uiState.ingredients,
                        ingredientError = uiState.ingredientError,
                        onAddIngredient = onAddIngredient,
                        onRemoveIngredient = onRemoveIngredient
                    )
                    2 -> Step3Review(
                        uiState = uiState,
                        onSave = onSave
                    )
                }
            }

            // Bottom navigation buttons
            if (uiState.currentStep < 2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (uiState.canGoBack) {
                        SecondaryButton(
                            text = "Back",
                            onClick = onPreviousStep,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    PrimaryButton(
                        text = "Next",
                        onClick = onNextStep,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────
// Step Indicator
// ─────────────────────────────────────────────────────────────

@Composable
private fun StepIndicator(
    currentStep: Int,
    labels: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            progress = { (currentStep + 1f) / labels.size },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            labels.forEachIndexed { index, label ->
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = if (index == currentStep) FontWeight.Bold else FontWeight.Normal,
                    color = if (index <= currentStep) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────
// Step 1: Recipe Details
// ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Step1Details(
    uiState: CreateRecipeUiState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBaseServingSizeChange: (Int) -> Unit,
    onUseMinServingChange: (Boolean) -> Unit,
    onMinServingSizeChange: (Int) -> Unit,
    onUseMaxServingChange: (Boolean) -> Unit,
    onMaxServingSizeChange: (Int) -> Unit,
    onToggleTag: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier.height(4.dp))

        SousChefTextField(
            value = uiState.title,
            onValueChange = onTitleChange,
            label = "Recipe Title *",
            isError = uiState.titleError != null,
            errorMessage = uiState.titleError
        )

        SousChefTextField(
            value = uiState.description,
            onValueChange = onDescriptionChange,
            label = "Description",
            singleLine = false,
            minLines = 3,
            maxLines = 5
        )

        // Serving Size
        SectionHeader(title = "Serving Size")
        ServingStepper(
            label = "Base servings",
            value = uiState.baseServingSize,
            onValueChange = onBaseServingSizeChange,
            min = 1,
            max = 50
        )

        // Min/Max restrictions
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Minimum servings",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = uiState.useMinServing,
                onCheckedChange = onUseMinServingChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
        if (uiState.useMinServing) {
            ServingStepper(
                label = "Min",
                value = uiState.minServingSize ?: 1,
                onValueChange = onMinServingSizeChange,
                min = 1,
                max = uiState.baseServingSize
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Maximum servings",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = uiState.useMaxServing,
                onCheckedChange = onUseMaxServingChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
        if (uiState.useMaxServing) {
            ServingStepper(
                label = "Max",
                value = uiState.maxServingSize ?: (uiState.baseServingSize * 2),
                onValueChange = onMaxServingSizeChange,
                min = uiState.baseServingSize,
                max = 100
            )
        }

        // Tags
        SectionHeader(title = "Tags")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RECIPE_TAGS.forEach { tag ->
                SousChefFilterChip(
                    label = tag,
                    selected = tag in uiState.selectedTags,
                    onSelectedChange = { onToggleTag(tag) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))
    }
}

// ─────────────────────────────────────────────────────────────
// Serving Stepper
// ─────────────────────────────────────────────────────────────

@Composable
private fun ServingStepper(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    min: Int,
    max: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { if (value > min) onValueChange(value - 1) },
                enabled = value > min
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text("−", style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.width(40.dp),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = { if (value < max) onValueChange(value + 1) },
                enabled = value < max
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────
// Step 2: Ingredients
// ─────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Step2Ingredients(
    ingredients: List<Ingredient>,
    ingredientError: String?,
    onAddIngredient: (Ingredient) -> Unit,
    onRemoveIngredient: (String) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SectionHeader(title = "Ingredients (${ingredients.size})")
            IconButton(onClick = { showBottomSheet = true }) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = "Add ingredient",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        if (ingredientError != null) {
            Text(
                text = ingredientError,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        if (ingredients.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Outlined.Restaurant,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "No ingredients yet",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(8.dp))
                    GhostButton(text = "Tap + to add", onClick = { showBottomSheet = true })
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(ingredients, key = { _, it -> it.ingredientId }) { _, ingredient ->
                    IngredientCard(
                        ingredient = ingredient,
                        onDelete = { onRemoveIngredient(ingredient.ingredientId) }
                    )
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface,
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            AddIngredientBottomSheet(
                onSave = { ingredient ->
                    onAddIngredient(ingredient)
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        showBottomSheet = false
                    }
                },
                onDismiss = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        showBottomSheet = false
                    }
                }
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────
// Ingredient Card
// ─────────────────────────────────────────────────────────────

@Composable
private fun IngredientCard(
    ingredient: Ingredient,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = ingredient.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "${ingredient.quantity} ${ingredient.unit}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (ingredient.spiceIntensityValue > 0 || ingredient.sweetnessValue > 0 || ingredient.saltnessValue > 0) {
                    Spacer(Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (ingredient.spiceIntensityValue > 0)
                            FlavorBadge("🌶 ${ingredient.spiceIntensityValue.toInt()}")
                        if (ingredient.sweetnessValue > 0)
                            FlavorBadge("🍯 ${ingredient.sweetnessValue.toInt()}")
                        if (ingredient.saltnessValue > 0)
                            FlavorBadge("🧂 ${ingredient.saltnessValue.toInt()}")
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "Remove",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun FlavorBadge(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 6.dp, vertical = 2.dp)
    )
}

// ─────────────────────────────────────────────────────────────
// Add Ingredient Bottom Sheet
// ─────────────────────────────────────────────────────────────

@Composable
private fun AddIngredientBottomSheet(
    onSave: (Ingredient) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var quantityText by remember { mutableStateOf("") }
    var selectedUnit by remember { mutableStateOf("grams") }
    var isDispensable by remember { mutableStateOf(false) }
    var showAdvanced by remember { mutableStateOf(false) }
    var spice by remember { mutableDoubleStateOf(0.0) }
    var sweetness by remember { mutableDoubleStateOf(0.0) }
    var saltiness by remember { mutableDoubleStateOf(0.0) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var quantityError by remember { mutableStateOf<String?>(null) }
    var unitExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Add Ingredient",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        SousChefTextField(
            value = name,
            onValueChange = { name = it; nameError = null },
            label = "Ingredient Name *",
            isError = nameError != null,
            errorMessage = nameError
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SousChefTextField(
                value = quantityText,
                onValueChange = { quantityText = it; quantityError = null },
                label = "Quantity *",
                isError = quantityError != null,
                errorMessage = quantityError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.weight(1f)
            )

            // Unit dropdown
            Box(modifier = Modifier.weight(1f)) {
                SousChefTextField(
                    value = selectedUnit,
                    onValueChange = {},
                    label = "Unit",
                    trailingIcon = {
                        IconButton(onClick = { unitExpanded = !unitExpanded }) {
                            Icon(
                                if (unitExpanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                                contentDescription = "Select unit"
                            )
                        }
                    },
                    modifier = Modifier.clickable { unitExpanded = true }
                )
                DropdownMenu(
                    expanded = unitExpanded,
                    onDismissRequest = { unitExpanded = false }
                ) {
                    UNIT_OPTIONS.forEach { unit ->
                        DropdownMenuItem(
                            text = { Text(unit) },
                            onClick = {
                                selectedUnit = unit
                                unitExpanded = false
                            }
                        )
                    }
                }
            }
        }

        // Dispensable toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Hardware dispensable",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    "Can the smart dispenser handle this?",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = isDispensable,
                onCheckedChange = { isDispensable = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }

        // Advanced flavor settings
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showAdvanced = !showAdvanced },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Flavor Profile (Advanced)",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            Icon(
                if (showAdvanced) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        if (showAdvanced) {
            FlavorSlider("🌶 Spice Intensity", spice) { spice = it }
            FlavorSlider("🍯 Sweetness", sweetness) { sweetness = it }
            FlavorSlider("🧂 Saltiness", saltiness) { saltiness = it }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondaryButton(
                text = "Cancel",
                onClick = onDismiss,
                modifier = Modifier.weight(1f)
            )
            PrimaryButton(
                text = "Add",
                onClick = {
                    var valid = true
                    if (name.isBlank()) { nameError = "Name required"; valid = false }
                    val qty = quantityText.toDoubleOrNull()
                    if (qty == null || qty <= 0) { quantityError = "Enter a valid quantity"; valid = false }
                    if (!valid) return@PrimaryButton

                    onSave(
                        Ingredient(
                            ingredientId = UUID.randomUUID().toString(),
                            name = name.trim(),
                            quantity = qty!!,
                            unit = selectedUnit,
                            isDispensable = isDispensable,
                            spiceIntensityValue = spice,
                            sweetnessValue = sweetness,
                            saltnessValue = saltiness
                        )
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun FlavorSlider(
    label: String,
    value: Double,
    onValueChange: (Double) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                value.toInt().toString(),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Slider(
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toDouble()) },
            valueRange = 0f..10f,
            steps = 9,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }
}

// ─────────────────────────────────────────────────────────────
// Step 3: Review & Save
// ─────────────────────────────────────────────────────────────

@Composable
private fun Step3Review(
    uiState: CreateRecipeUiState,
    onSave: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier.height(4.dp))

        // Summary card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = uiState.title.ifBlank { "Untitled Recipe" },
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                if (uiState.description.isNotBlank()) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = uiState.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 3
                    )
                }
                Spacer(Modifier.height(12.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Spacer(Modifier.height(12.dp))

                ReviewRow("Base Servings", uiState.baseServingSize.toString())
                uiState.minServingSize?.let { ReviewRow("Min Servings", it.toString()) }
                uiState.maxServingSize?.let { ReviewRow("Max Servings", it.toString()) }
                ReviewRow("Ingredients", uiState.ingredients.size.toString())

                if (uiState.selectedTags.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = uiState.selectedTags.joinToString(" · "),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Ingredient list preview
        if (uiState.ingredients.isNotEmpty()) {
            SectionHeader(title = "Ingredients")
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column {
                    uiState.ingredients.forEachIndexed { index, ingredient ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                ingredient.name,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                "${ingredient.quantity} ${ingredient.unit}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        if (index < uiState.ingredients.lastIndex) {
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // Save buttons
        SecondaryButton(
            text = "Save as Draft",
            onClick = { onSave(false) },
            isLoading = uiState.isLoading
        )

        PrimaryButton(
            text = "Save & Publish",
            onClick = { onSave(true) },
            isLoading = uiState.isLoading
        )

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun ReviewRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface)
    }
}

// ─────────────────────────────────────────────────────────────
// Previews
// ─────────────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CreateRecipeStep1Preview() {
    SousChefTheme {
        CreateRecipeScreenLayout(
            uiState = CreateRecipeUiState(currentStep = 0, title = "Truffle Risotto"),
            snackbarHostState = remember { SnackbarHostState() },
            onBack = {}, onNextStep = {}, onPreviousStep = {},
            onTitleChange = {}, onDescriptionChange = {},
            onBaseServingSizeChange = {}, onUseMinServingChange = {},
            onMinServingSizeChange = {}, onUseMaxServingChange = {},
            onMaxServingSizeChange = {}, onToggleTag = {},
            onAddIngredient = {}, onRemoveIngredient = {}, onSave = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CreateRecipeStep2Preview() {
    SousChefTheme {
        CreateRecipeScreenLayout(
            uiState = CreateRecipeUiState(
                currentStep = 1,
                ingredients = listOf(
                    Ingredient("1", "Arborio Rice", 200.0, "grams", spiceIntensityValue = 0.0),
                    Ingredient("2", "Parmesan Cheese", 80.0, "grams", sweetnessValue = 2.0),
                    Ingredient("3", "White Truffle Oil", 2.0, "tbsp", spiceIntensityValue = 1.0)
                )
            ),
            snackbarHostState = remember { SnackbarHostState() },
            onBack = {}, onNextStep = {}, onPreviousStep = {},
            onTitleChange = {}, onDescriptionChange = {},
            onBaseServingSizeChange = {}, onUseMinServingChange = {},
            onMinServingSizeChange = {}, onUseMaxServingChange = {},
            onMaxServingSizeChange = {}, onToggleTag = {},
            onAddIngredient = {}, onRemoveIngredient = {}, onSave = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CreateRecipeStep3Preview() {
    SousChefTheme {
        CreateRecipeScreenLayout(
            uiState = CreateRecipeUiState(
                currentStep = 2,
                title = "Truffle Risotto alla Milanese",
                description = "A rich and creamy Italian risotto with truffle oil and Parmesan.",
                baseServingSize = 4,
                selectedTags = listOf("🇮🇹 Italian", "🌿 Vegetarian"),
                ingredients = listOf(
                    Ingredient("1", "Arborio Rice", 200.0, "grams"),
                    Ingredient("2", "Parmesan Cheese", 80.0, "grams"),
                    Ingredient("3", "White Truffle Oil", 2.0, "tbsp")
                )
            ),
            snackbarHostState = remember { SnackbarHostState() },
            onBack = {}, onNextStep = {}, onPreviousStep = {},
            onTitleChange = {}, onDescriptionChange = {},
            onBaseServingSizeChange = {}, onUseMinServingChange = {},
            onMinServingSizeChange = {}, onUseMaxServingChange = {},
            onMaxServingSizeChange = {}, onToggleTag = {},
            onAddIngredient = {}, onRemoveIngredient = {}, onSave = {}
        )
    }
}

