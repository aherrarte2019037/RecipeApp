@file:Suppress("UNUSED_PARAMETER")

package com.group5.recipeapp.presentation.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.group5.recipeapp.model.Ingredient
import com.group5.recipeapp.ui.theme.Typography

@Composable
fun RecipePage(
    navController: NavHostController,
    recipeId: Int,
    viewModel: RecipesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)  {
    // Fetch recipe details based on the provided recipeId
    viewModel.fetchRecipeById(recipeId)
    val scrollState = rememberScrollState()
    // Collect recipe details from the view model
    val recipeDetails by viewModel.recipeDetails.collectAsState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        recipeDetails?.let { details ->
            RecipeImage(imageUrl = details.image)
            RecipeTitle(title = details.title)
            CookingInfo(
                servings = details.servings,
                readyInMinutes = details.readyInMinutes,
                healthScore = details.healthScore,
                spoonacularScore = details.spoonacularScore
            )
            details.ingredients?.let { IngredientsList(ingredients = it) }
            details.instructions?.let { Instructions(instructions = it) }
            details.sourceUrl?.let {
                AdditionalDetails(
                    sourceUrl = it,
                )
            }

        } ?: run {
            // Show a loading indicator if recipeDetails is null
            CircularProgressIndicator()
        }
    }
}

// Composable function for displaying the recipe image
@Composable
fun RecipeImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "Recipe Image",
        modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

// Composable function for displaying the recipe title
@Composable
fun RecipeTitle(title: String) {
    Text(
        text = title,
        style = Typography.titleLarge.copy(
            fontWeight = FontWeight.SemiBold,
        ),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

// Composable function for displaying cooking information
@Composable
fun CookingInfo(
    servings: Int?,
    readyInMinutes: Int?,
    healthScore: Float?,
    spoonacularScore: Float?
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        LabelWithBorder(label = "Servings", content = servings?.toString() ?: "No info available")
        LabelWithBorder(label = "Ready in", content = readyInMinutes?.toString()?.plus(" minutes") ?: "No info available")
        LabelWithBorder(label = "Health Score", content = healthScore?.toString() ?: "No info available")
        LabelWithBorder(label = "Spoonacular Score", content = spoonacularScore?.toString() ?: "No info available")
    }
}

// Composable function for displaying a label with border and content
@Composable
fun LabelWithBorder(label: String, content: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, style = Typography.bodyMedium.copy(fontWeight = FontWeight.Medium))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = content, style = Typography.bodyMedium.copy(fontWeight = FontWeight.Normal))
    }
}

// Composable function for displaying a list of ingredients
@Composable
fun IngredientsList(ingredients: List<Ingredient>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Ingredients", style = Typography.bodyLarge.copy(
            fontWeight = FontWeight.SemiBold,
        ))
        ingredients.forEach { ingredient ->
            Text("${ingredient.amount} ${ingredient.unit} of ${ingredient.name}")
        }
    }
}

// Composable function for displaying recipe instructions
@Composable
fun Instructions(instructions: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Instructions", style = Typography.bodyLarge.copy(
            fontWeight = FontWeight.SemiBold,
        ))
        Text(instructions)
    }
}

// Composable function for displaying additional details like the source URL
@Composable
fun AdditionalDetails(sourceUrl: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Source: $sourceUrl")
    }
}