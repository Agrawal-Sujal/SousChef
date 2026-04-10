package com.souschef.api

import com.souschef.model.recipe.ResolvedIngredient

/**
 * Prompt engineering for Gemini recipe step generation.
 *
 * Produces a structured system prompt that instructs Gemini to return
 * a JSON array of **atomic, single-ingredient** cooking steps.
 *
 * Key design rules enforced by the prompt:
 * - Every ingredient addition is its own step (never "add A, B, and C").
 * - Quantities are NEVER hard-coded in instructionText.
 * - Each step declares a `stepType` and optionally one `ingredientName`.
 * - `quantityMultiplier` expresses what fraction of the ingredient's total
 *   recipe amount is used at that particular step.
 */
object GeminiRecipePrompt {

    /**
     * Builds the full prompt for Gemini, injecting the recipe description
     * and ingredient list into the template.
     *
     * @param recipeDescription Free-text description of the recipe by the creator.
     * @param ingredients Resolved ingredient list with names and quantities.
     * @return The complete prompt string to send to the Gemini model.
     */
    fun buildPrompt(
        recipeDescription: String,
        ingredients: List<ResolvedIngredient>
    ): String {
        val ingredientList = ingredients.joinToString("\n") { ingredient ->
            "- ${ingredient.name}: ${ingredient.quantity} ${ingredient.unit}"
        }

        return """
You are a professional chef and recipe writer. Your task is to take a recipe description and convert it into a structured list of ATOMIC cooking steps.

CRITICAL RULES:
1. Every ingredient addition MUST be its own separate step. NEVER combine multiple ingredients in a single step. For example, "add onions, garlic, and ginger" must become THREE separate steps.
2. NEVER hard-code quantities in instructionText. Write "Add the garlic" not "Add 4 cloves of garlic". Quantities are calculated dynamically by the app.
3. Each step must be a single, specific, executable action.
4. Include all implicit preparation steps (washing, chopping, preheating, etc.).
5. Steps must be sequenced in the correct cooking order.

STEP TYPES:
- "INGREDIENT" — Adding a single ingredient to the dish. Must have ingredientName and quantityMultiplier.
- "ACTION" — A cooking action (stir, heat, simmer, rest). No ingredient.
- "PREP" — Preparation step (wash, chop, dice, preheat). May reference one ingredient being prepared.

QUANTITY MULTIPLIER:
- This is a decimal between 0.0 and 1.0 representing what fraction of the ingredient's TOTAL recipe quantity is used at this step.
- Default is 1.0 (use all of the ingredient at once).
- If an ingredient is split across multiple steps (e.g. "add half the onions now, rest later"), use 0.5 for each.
- The sum of all quantityMultiplier values for the same ingredient across all steps should equal 1.0.

Return a JSON array. Each object must have these fields:
- stepNumber: Int (starting from 1)
- stepType: String ("INGREDIENT", "ACTION", or "PREP")
- instructionText: String (NO quantities — only the action and ingredient name)
- ingredientName: String or null (the EXACT ingredient name from the list below, null for ACTION steps)
- quantityMultiplier: Double (0.0–1.0, default 1.0, only meaningful when ingredientName is present)
- timerSeconds: Int or null (only if a specific time is mentioned or commonly needed)
- flameLevel: "low" or "medium" or "high" or null (only for stovetop steps)
- expectedVisualCue: String or null (what to look for to know this step is done)

Recipe Description:
$recipeDescription

Ingredients:
$ingredientList

Return ONLY the JSON array. No explanation. No markdown. No code fences. Just the raw JSON array.
        """.trimIndent()
    }
}
