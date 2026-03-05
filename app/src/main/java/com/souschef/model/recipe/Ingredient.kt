package com.souschef.model.recipe

/**
 * Embedded sub-document within a Recipe.
 * Represents a single ingredient with quantity and flavor attributes.
 *
 * All fields have defaults for Firestore deserialization.
 */
data class Ingredient(
    val ingredientId: String = "",
    val name: String = "",
    val quantity: Double = 0.0,
    val unit: String = "grams",
    val perPersonQuantity: Double = 0.0,
    val isDispensable: Boolean = false,
    val spiceIntensityValue: Double = 0.0,
    val sweetnessValue: Double = 0.0,
    val saltnessValue: Double = 0.0
)

