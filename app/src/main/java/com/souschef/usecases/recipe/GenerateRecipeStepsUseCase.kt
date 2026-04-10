package com.souschef.usecases.recipe

import com.souschef.model.ingredient.GlobalIngredient
import com.souschef.model.recipe.RecipeStep
import com.souschef.model.recipe.ResolvedIngredient
import com.souschef.repository.ai.AiRepository
import com.souschef.repository.ingredient.IngredientRepository
import com.souschef.util.IngredientMatcher
import com.souschef.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Generates atomic recipe steps from a description using AI, then resolves every
 * AI-generated ingredient name to a [GlobalIngredient.ingredientId] using
 * [IngredientMatcher] (Dice-coefficient fuzzy match, ≥ 75% similarity).
 *
 * Each step references at most **one** ingredient via [RecipeStep.ingredientId].
 * Steps whose ingredient name cannot be resolved have their [ingredientId] set to null
 * (the instruction text still mentions the ingredient by name for manual reference).
 */
class GenerateRecipeStepsUseCase(
    private val aiRepository: AiRepository,
    private val ingredientRepository: IngredientRepository
) {

    /**
     * @param description  Free-text recipe description from the creator.
     * @param ingredients  Resolved ingredients for this recipe (for building the AI prompt).
     * @return Flow emitting Loading → Success(steps with resolved ingredient IDs) or Failure.
     */
    fun execute(
        description: String,
        ingredients: List<ResolvedIngredient>
    ): Flow<Resource<List<RecipeStep>>> {
        return aiRepository.generateRecipeSteps(description, ingredients)
            .map { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val allGlobalIngredients: List<GlobalIngredient> = try {
                            ingredientRepository.getAllIngredients()
                                .first()
                        } catch (_: Exception) {
                            emptyList()
                        }

                        val resolvedSteps = resolveIngredientIds(
                            steps = resource.data,
                            globalIngredients = allGlobalIngredients
                        )
                        Resource.success(resolvedSteps)
                    }
                    is Resource.Failure -> resource
                    is Resource.Loading -> resource
                }
            }
    }

    /**
     * For each step, resolves [RecipeStep.ingredientId] (which initially contains
     * the raw AI-generated ingredient name) to an actual globalIngredientId via
     * fuzzy matching.
     *
     * If no match is found, `ingredientId` is set to null — the step's instruction
     * text still references the ingredient by name for the user.
     */
    private fun resolveIngredientIds(
        steps: List<RecipeStep>,
        globalIngredients: List<GlobalIngredient>
    ): List<RecipeStep> {
        return steps.map { step ->
            val rawName = step.ingredientId // temporarily holds the name from AI
            if (rawName.isNullOrBlank()) {
                // ACTION step or no ingredient — leave as-is
                step
            } else {
                val match = IngredientMatcher.fuzzyMatch(rawName, globalIngredients)
                step.copy(
                    ingredientId = match?.ingredientId // resolved ID or null
                )
            }
        }
    }
}
