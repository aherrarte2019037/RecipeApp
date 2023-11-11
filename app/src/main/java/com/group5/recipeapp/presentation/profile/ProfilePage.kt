package com.group5.recipeapp.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.group5.recipeapp.R
import com.group5.recipeapp.ui.theme.Red
import com.radusalagean.infobarcompose.InfoBar
import com.radusalagean.infobarcompose.InfoBarMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(
    navController: NavHostController,
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var snackbarMessage: InfoBarMessage? by remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile", fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.profile_avatar),
                contentDescription = "Profile Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                label = { Text("Name", fontSize = 12.sp) }
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { viewModel.onSaveChanges(
                    onMessage = { message ->
                        snackbarMessage = InfoBarMessage(text = message)
                    }
                ) },
                modifier = Modifier
                    .width(285.dp)
                    .height(50.dp)
            ) {
                Text("Save changes")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.onSignOut {
                        navController.navigate("login") {
                            popUpTo(0)
                        }
                    }
                },
                modifier = Modifier
                    .width(285.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red)
            ) {
                Text("Sign out")
            }
        }
    }
    InfoBar(offeredMessage = snackbarMessage, shape = RectangleShape) {
        snackbarMessage = null
    }
}
