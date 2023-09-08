package com.group5.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.group5.recipeapp.model.Recipe
import com.group5.recipeapp.presentation.categories.CategoriesPages
import com.group5.recipeapp.presentation.login.LoginPage
import com.group5.recipeapp.presentation.recipes.RecipePage
import com.group5.recipeapp.presentation.register.RegisterPage
import com.group5.recipeapp.ui.theme.RecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHandler()
                }
            }
        }
    }
}

@Composable
fun NavigationHandler() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(navController)
        }
        composable("register") {
            RegisterPage(navController)
        }
        composable("categories") {
            CategoriesPages(navController)
        }
        composable("recipe/{id}") { navBackStackEntry ->
            val recipeId = navBackStackEntry.arguments?.getString("id") ?: ""
            val mockRecipe = Recipe(
                id = recipeId,
                name = "Galletas con chispas de chocolate",
                imageResourceId = R.drawable.food_bg, // Reemplaza con una referencia válida a una imagen
                preparationSteps = listOf(
                    "Precalienta el horno a 180°C.",
                    "En un tazón grande, mezcla la harina, el azúcar y el polvo de hornear.",
                    // ... (agrega los otros pasos aquí)
                ),
                ingredients = listOf(
                    "2 tazas de harina para todo uso",
                    "1 taza de azúcar",
                    // ... (agrega los otros ingredientes aquí)
                )
            )

            RecipePage(navController, mockRecipe)
        }
    }
}