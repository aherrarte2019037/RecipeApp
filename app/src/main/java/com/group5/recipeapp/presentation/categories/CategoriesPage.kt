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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.group5.recipeapp.presentation.components.RoundedButton
import com.group5.recipeapp.ui.theme.Black
import com.group5.recipeapp.ui.theme.Blue
import com.group5.recipeapp.ui.theme.LightBlue
import com.group5.recipeapp.ui.theme.Red
import com.group5.recipeapp.ui.theme.Typography
import com.group5.recipeapp.ui.theme.White

@Preview
@Composable
fun CategoriesPages() {
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
                text = "Breakfast",
                displayProgressBar = false,
                onClick = {}
            )
            Spacer(modifier = Modifier.size(20.dp))
            RoundedButton(
                bgColor = Blue,
                text = "Lunch",
                displayProgressBar = false,
                onClick = {}
            )
            Spacer(modifier = Modifier.size(20.dp))
            RoundedButton(
                bgColor = Black,
                text = "Dinner",
                displayProgressBar = false,
                onClick = {}
            )
            Spacer(modifier = Modifier.size(20.dp))
            RoundedButton(
                bgColor = LightBlue,
                text = "Dinner",
                displayProgressBar = false,
                onClick = {}
            )
        }
    }
}