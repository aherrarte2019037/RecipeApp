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

class RecipeListViewModel : ViewModel() {
    private val _recipes = MutableStateFlow<List<PreviewRecipe>>(emptyList())
    val recipes: StateFlow<List<PreviewRecipe>> = _recipes
    private var currentPage = 0
    private val pageSize = 20
    private var isLastPage = false
    var isLoading = false
    private var currentCategory = ""

    private val recipeService: RecipeService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeService::class.java)
    }

    fun fetchRecipes(category: String, isInitial: Boolean = currentCategory != category) {
        if (isInitial) {
            currentPage = 0
            isLastPage = false
            currentCategory = category
            _recipes.value = emptyList()
        } else if (isLastPage || isLoading) {
            return
        }

        isLoading = true

        viewModelScope.launch {
            try {
                val response = recipeService.searchRecipes(
                    query = category,
                    number = pageSize,
                    offset = currentPage * pageSize
                )
                currentPage++
                val fetchedRecipes = response.results
                if (fetchedRecipes.size < pageSize) {
                    isLastPage = true
                }
                _recipes.value = _recipes.value + fetchedRecipes
            } catch (_: Exception) {
            } finally {
                isLoading = false
            }
        }
    }
}
