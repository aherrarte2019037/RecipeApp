package com.group5.recipeapp.presentation.recipes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.group5.recipeapp.model.FoodCategoriesEnum

@Composable
fun RecipesList(navController: NavHostController, foodCategory: FoodCategoriesEnum) {
    Text(text = foodCategory.displayableText)
}