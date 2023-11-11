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
import com.google.firebase.auth.FirebaseAuth
import com.group5.recipeapp.io.LocalStorageService
import com.group5.recipeapp.presentation.categories.CategoriesPages
import com.group5.recipeapp.presentation.login.LoginPage
import com.group5.recipeapp.presentation.profile.ProfilePage
import com.group5.recipeapp.presentation.recipe_list.RecipeList
import com.group5.recipeapp.presentation.recipes.RecipePage
import com.group5.recipeapp.presentation.register.RegisterPage
import com.group5.recipeapp.ui.theme.RecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val localStorageService = LocalStorageService(this)

        setContent {
            RecipeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHandler(localStorageService)
                }
            }
        }
    }
}

@Composable
fun NavigationHandler(localStorageService: LocalStorageService) {
    val navController = rememberNavController()
    var startDestination = "login"
    val rememberSession = localStorageService.getBool("remember_session", false)

    if (rememberSession && FirebaseAuth.getInstance().currentUser?.email != null) {
        startDestination = "categories"
    } else {
        FirebaseAuth.getInstance().signOut()
    }

    NavHost(navController = navController, startDestination) {
        composable("login") {
                LoginPage(navController)
        }
        composable("register") {
            RegisterPage(navController)
        }
        composable("categories") {
            CategoriesPages(navController)
        }
        composable("profile") {
            ProfilePage(navController)
        }
        composable("recipe-list/{category}") { navBackStackEntry ->
            val category = navBackStackEntry.arguments?.getString("category") ?: ""
            RecipeList(navController = navController, category = category)
        }
        composable("recipe/{id}") { navBackStackEntry ->
            val recipeId = navBackStackEntry.arguments?.getString("id") ?: ""
            RecipePage(navController, recipeId.toInt())
        }
    }
}