package com.group5.recipeapp.presentation.recipes

data class Recipe(
    val name: String,
    val imageResourceId: Int, // Referencia al recurso de imagen
    val preparationSteps: String,
    val ingredients: String
)
