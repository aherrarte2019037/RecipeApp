package com.group5.recipeapp.model

enum class FoodCategoriesEnum(val value: String, val displayableText: String) {
    Breakfast("Breakfast","Breakfast"),
    Lunch("Lunch","Lunch"),
    Dinner("Dinner","Dinner"),
    Dessert("Dessert", "Dessert");

    companion object {
        fun byNameOrNull(input: String): FoodCategoriesEnum? {
            return FoodCategoriesEnum.values().firstOrNull { it.name == input }
        }
    }
}