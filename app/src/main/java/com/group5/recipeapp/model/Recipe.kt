package com.group5.recipeapp.model

data class Recipe(
    val id: String,
    val name: String,
    val imageResourceId: Int,
    val preparationSteps: List<String>,
    val ingredients: List<String>
)
