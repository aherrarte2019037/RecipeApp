package com.group5.recipeapp.presentation.recipe_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.group5.recipeapp.model.PreviewRecipe
import com.group5.recipeapp.ui.theme.Typography
import com.group5.recipeapp.ui.theme.White

@Composable
fun RecipeList(
    navController: NavHostController,
    category: String,
    viewModel: RecipeListViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    viewModel.fetchRecipes(category)
    val recipes = viewModel.recipes.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$category Recipes",
                style = Typography.titleLarge,
                modifier = Modifier.padding(top = 30.dp, bottom = 5.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecipeListView(recipes) { recipeId ->
                navigateToRecipe(navController, recipeId)
            }
        }
    }
}

@Composable
fun RecipeListView(
    recipes: List<PreviewRecipe>,
    onRecipeClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        items(recipes) { recipe ->
            RecipeListItem(recipe, onRecipeClicked)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RecipeListItem(
    recipe: PreviewRecipe,
    onRecipeClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRecipeClicked(recipe.id) }
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = recipe.image),
            contentDescription = recipe.title,
            modifier = Modifier
                .size(height = 90.dp, width = 100.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = recipe.title,
            style = Typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
            )
        )
    }
}

fun navigateToRecipe(navController: NavHostController, recipeId: Int) {
    navController.navigate("recipe/$recipeId")
}