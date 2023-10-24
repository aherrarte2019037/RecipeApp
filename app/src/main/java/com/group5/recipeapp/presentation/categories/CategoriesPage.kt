package com.group5.recipeapp.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.group5.recipeapp.model.FoodCategoriesEnum
import com.group5.recipeapp.presentation.components.RoundedButton
import com.group5.recipeapp.presentation.login.LoginViewModel
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
                    navigateToRecipe(navController)
                }
            )
            Spacer(modifier = Modifier.size(20.dp))
            RoundedButton(
                bgColor = Blue,
                text = FoodCategoriesEnum.Lunch.displayableText,
                displayProgressBar = false,
                onClick = {
                    navigateToRecipe(navController)
                }
            )
            Spacer(modifier = Modifier.size(20.dp))
            RoundedButton(
                bgColor = Black,
                text = FoodCategoriesEnum.Dinner.displayableText,
                displayProgressBar = false,
                onClick = {
                    navigateToRecipe(navController)
                }
            )
            Spacer(modifier = Modifier.size(20.dp))
            RoundedButton(
                bgColor = LightBlue,
                text = FoodCategoriesEnum.Dessert.displayableText,
                displayProgressBar = false,
                onClick = {
                    navigateToRecipe(navController)
                }
            )
            Spacer(modifier = Modifier.size(100.dp))
            RoundedButton(
                bgColor = Color.Unspecified,
                text = "Sign out",
                textColor = Black,
                textSize = 18.sp,
                displayProgressBar = false,
                onClick = {
                    viewModel.signOut {
                        navController.navigate("login") {
                            popUpTo(0)
                        }
                    }
                }
            )
        }
    }
}

fun navigateByFoodCategory(navController: NavHostController, foodCategory: FoodCategoriesEnum) {
    val foodCategoryParam = foodCategory.value
    navController.navigate("$foodCategoryParam/recipes-list")
}

fun navigateToRecipe(navController: NavHostController) {
    val mockId = "mockId"
    navController.navigate("recipe/${mockId}")
}