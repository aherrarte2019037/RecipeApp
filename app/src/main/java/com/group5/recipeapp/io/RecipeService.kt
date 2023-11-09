package com.group5.recipeapp.io

import com.group5.recipeapp.model.PreviewRecipe
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String = "92e0bbf6019f4769818922a0a01d7277"
    ): RecipeSearchResponse
}

data class RecipeSearchResponse(
    val results: List<PreviewRecipe>
)