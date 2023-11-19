package com.group5.recipeapp.presentation.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.group5.recipeapp.R
import com.group5.recipeapp.model.FoodCategoriesEnum
import com.group5.recipeapp.presentation.components.RoundedButton
import com.group5.recipeapp.ui.theme.Black
import com.group5.recipeapp.ui.theme.Blue
import com.group5.recipeapp.ui.theme.LightBlue
import com.group5.recipeapp.ui.theme.Red
import com.group5.recipeapp.ui.theme.Typography
import com.group5.recipeapp.ui.theme.White

@Composable
fun CategoriesPages(
    navController: NavHostController,
    viewModel: CategoriesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    // Main container for the categories page
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title for selecting a food category
            Text(
                text = "Select a food category",
                style = Typography.titleLarge,
                modifier = Modifier.padding(top = 10.dp)
            )
            Spacer(modifier = Modifier.size(35.dp))
            RoundedButton(
                bgColor = Red,
                text = FoodCategoriesEnum.Breakfast.displayableText,
                displayProgressBar = false,
                onClick = {
                    navigateByFoodCategory(navController, FoodCategoriesEnum.Breakfast)
                }
            )
            Spacer(modifier = Modifier.size(20.dp))
            RoundedButton(
                bgColor = Blue,
                text = FoodCategoriesEnum.Lunch.displayableText,
                displayProgressBar = false,
                onClick = {
                    navigateByFoodCategory(navController, FoodCategoriesEnum.Lunch)
                }
            )
            Spacer(modifier = Modifier.size(20.dp))
            RoundedButton(
                bgColor = Black,
                text = FoodCategoriesEnum.Dinner.displayableText,
                displayProgressBar = false,
                onClick = {
                    navigateByFoodCategory(navController, FoodCategoriesEnum.Dinner)
                }
            )
            Spacer(modifier = Modifier.size(20.dp))
            RoundedButton(
                bgColor = LightBlue,
                text = FoodCategoriesEnum.Dessert.displayableText,
                displayProgressBar = false,
                onClick = {
                    navigateByFoodCategory(navController, FoodCategoriesEnum.Dessert)
                }
            )
            Spacer(modifier = Modifier.size(100.dp))

            // Button for navigating to the user's profile
            ProfileAvatarWithButton(navController)
        }
    }
}

@Composable
fun ProfileAvatarWithButton(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable {
                navController.navigate("profile")
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_avatar),
            contentDescription = "Profile Avatar",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "My Profile",
            fontWeight = FontWeight.Medium,
        )
    }
}


// Function to navigate to the recipe list based on the selected food category
fun navigateByFoodCategory(navController: NavHostController, foodCategory: FoodCategoriesEnum) {
    val foodCategoryParam = foodCategory.value
    navController.navigate("recipe-list/$foodCategoryParam")
}