package com.group5.recipeapp.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group5.recipeapp.ui.theme.Blue
import com.group5.recipeapp.ui.theme.Typography
import com.group5.recipeapp.ui.theme.White

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = White,
    textSize: TextUnit = 14.sp,
    displayProgressBar: Boolean = false,
    bgColor: Color = Blue,
    onClick: () -> Unit,
) {
    if(!displayProgressBar) {
        Button(
            modifier = modifier.width(280.dp).height(50.dp),
            onClick = onClick,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = bgColor)
        ) {
            Text(
                text = text,
                style = Typography.labelLarge.copy(
                    color = textColor,
                    fontSize = textSize,
                )
            )
        }
    } else {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = Blue,
            strokeWidth = 6.dp
        )
    }
}