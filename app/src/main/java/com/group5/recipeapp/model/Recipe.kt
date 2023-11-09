package com.group5.recipeapp.model

data class RecipeDetails(
    val id: Int,
    val title: String,
    val image: String,
    val servings: Int?,
    val readyInMinutes: Int?,
    val sourceUrl: String?,
    val healthScore: Float?,
    val spoonacularScore: Float?,
    val pricePerServing: Float?,
    val instructions: String?,
    val ingredients: List<Ingredient>?
)

data class Ingredient(
    val id: Int,
    val name: String,
    val image: String,
    val amount: Float,
    val unit: String,
    val original: String // The description as it appears in the recipe instructions
)

data class PreviewRecipe(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String
)