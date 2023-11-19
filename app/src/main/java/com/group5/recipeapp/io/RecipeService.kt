package com.group5.recipeapp.io

import com.group5.recipeapp.model.PreviewRecipe
import com.group5.recipeapp.model.RecipeDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Este código define una interfaz RecipeService que actúa como una interfaz para
// realizar solicitudes a una API de recetas. Está utilizando Retrofit,
// una biblioteca de Android que facilita la comunicación con servicios web REST.


interface RecipeService {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("number") number: Number,
        @Query("offset") offset: Number,
        @Query("apiKey") apiKey: String = "92e0bbf6019f4769818922a0a01d7277"
    ): RecipeSearchResponse

    @GET("recipes/{id}/information")
    suspend fun getRecipeById(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean = false,
        @Query("apiKey") apiKey: String = "92e0bbf6019f4769818922a0a01d7277"
    ): RecipeDetails
}

data class RecipeSearchResponse(
    val results: List<PreviewRecipe>
)