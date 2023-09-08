package com.group5.recipeapp.presentation.recipes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.group5.recipeapp.R
import com.group5.recipeapp.model.Recipe
import com.group5.recipeapp.ui.theme.Black
import com.group5.recipeapp.ui.theme.Typography

@Composable
fun RecipePage(
    navController: NavHostController,
    recipe: Recipe,
) {
    val titleStyle = Typography.titleMedium
    val bodyStyle = Typography.bodySmall

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sección de la foto de la receta
        RecipeImage(imageResource = R.drawable.food_bg)

        Spacer(modifier = Modifier.height(15.dp))

        // Sección de los pasos de preparación
        SectionTitle(title = "Pasos de preparación", style = titleStyle, icon = Icons.Default.List)

        recipe.preparationSteps.forEachIndexed { index, step ->
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
            ) {
                Text(text = "${index + 1}.", style = bodyStyle, modifier = Modifier.width(28.dp))
                Text(text = step, style = bodyStyle)
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Sección de los ingredientes
        SectionTitle(title = "Ingredientes", style = titleStyle, icon = Icons.Default.LocalDining)

        recipe.ingredients.forEach { ingredient ->
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
            ) {
                Text(text = "•", style = bodyStyle.copy(fontWeight = FontWeight.Bold), modifier = Modifier.width(28.dp))
                Text(text = ingredient, style = bodyStyle)
            }
        }
    }
}

@Composable
fun RecipeImage(imageResource: Int) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Recipe Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Black.copy(alpha = 0.2f)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun SectionTitle(title: String, style: TextStyle, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, style = style)
    }
}
