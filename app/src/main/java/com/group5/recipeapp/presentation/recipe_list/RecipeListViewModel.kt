package com.group5.recipeapp.presentation.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group5.recipeapp.io.RecipeService
import com.group5.recipeapp.model.PreviewRecipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeListViewModel: ViewModel() {
    private val _recipes = MutableStateFlow<List<PreviewRecipe>>(emptyList())
    val recipes: StateFlow<List<PreviewRecipe>> = _recipes

    private val recipeService: RecipeService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeService::class.java)
    }

    fun fetchRecipes(category: String) {
        viewModelScope.launch {
            try {
                val response = recipeService.searchRecipes(
                    query = category,
                )
                _recipes.value = response.results
            } catch (e: Exception) {
                _recipes.value = emptyList()
            }
        }
    }
}