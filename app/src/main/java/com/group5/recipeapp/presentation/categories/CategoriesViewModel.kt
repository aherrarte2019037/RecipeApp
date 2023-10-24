package com.group5.recipeapp.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class CategoriesViewModel: ViewModel() {
    fun signOut(login: () -> Unit) {
        viewModelScope.launch {
            try {
                FirebaseAuth.getInstance().signOut()
                login()

            } catch (_: Exception) {}
        }
    }
}