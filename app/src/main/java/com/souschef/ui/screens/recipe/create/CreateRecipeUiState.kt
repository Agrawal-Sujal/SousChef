package com.souschef.ui.screens.recipe.create

import android.net.Uri
import com.souschef.model.ingredient.GlobalIngredient
import com.souschef.model.recipe.RecipeIngredient
import com.souschef.model.recipe.RecipeStep
import com.souschef.model.recipe.RecipeTag

/**
 * UI state for the unified Create / Edit Recipe wizard.
 *
 * ## Steps
 * 0 — Details  (title, description, servings, tags, cover image)
 * 1 — Ingredients  (pick from global library)
 * 2 — Cooking Steps  (AI-generate or add manually — **optional, skippable**)
 * 3 — Review & Save
 */
data class CreateRecipeUiState(
    // ── Wizard navigation ────────────────────────────────────
    val currentStep: Int = 0,
    val totalSteps: Int = 4,

    // ── Step 1: Recipe Details ───────────────────────────────
    val title: String = "",
    val description: String = "",
    val baseServingSize: Int = 4,
    val minServingSize: Int? = null,
    val maxServingSize: Int? = null,
    val useMinServing: Boolean = false,
    val useMaxServing: Boolean = false,
    val selectedTags: List<RecipeTag> = emptyList(),
    val coverImageUri: Uri? = null,

    // ── Step 2: Ingredients ─────────────────────────────────
    val ingredients: List<RecipeIngredient> = emptyList(),
    val globalIngredients: List<GlobalIngredient> = emptyList(),

    // ── Step 3: Cooking Steps (AI / Manual) ─────────────────
    val aiDescription: String = "",
    val ingredientChips: List<String> = emptyList(),
    val steps: List<RecipeStep> = emptyList(),
    val stepsStage: StepsStage = StepsStage.INPUT,
    val isGeneratingSteps: Boolean = false,

    // ── Validation ──────────────────────────────────────────
    val titleError: String? = null,
    val ingredientError: String? = null,

    // ── Loading / result ────────────────────────────────────
    val isLoading: Boolean = false,
    val isUploadingImage: Boolean = false,
    val isSaved: Boolean = false,
    val savedRecipeId: String? = null,
    val generalError: String? = null
) {
    val stepLabels: List<String> = listOf("Details", "Ingredients", "Steps", "Review")
    val canGoNext: Boolean get() = currentStep < totalSteps - 1
    val canGoBack: Boolean get() = currentStep > 0

    /**
     * Sub-stages within the Cooking Steps step.
     */
    enum class StepsStage {
        /** User hasn't generated yet — show description input + generate button. */
        INPUT,
        /** AI generation in progress. */
        LOADING,
        /** Steps generated/loaded — show editable step cards. */
        REVIEW
    }
}
