package com.group5.recipeapp.presentation.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group5.recipeapp.io.RecipeService
import com.group5.recipeapp.model.RecipeDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipesViewModel: ViewModel() {
    private val _recipeDetails = MutableStateFlow<RecipeDetails?>(null)
    val recipeDetails: StateFlow<RecipeDetails?> = _recipeDetails

    private val recipeService: RecipeService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeService::class.java)
    }

    fun fetchRecipeById(id: Int) {
        viewModelScope.launch {
            try {
                val response = recipeService.getRecipeById(id)
                _recipeDetails.value = response
            } catch (e: Exception) {
                _recipeDetails.value = null
            }
        }
    }
}