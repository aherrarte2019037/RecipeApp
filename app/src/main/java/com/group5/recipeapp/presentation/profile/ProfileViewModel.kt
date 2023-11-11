package com.group5.recipeapp.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    var name by mutableStateOf("")

    init {
        name = FirebaseAuth.getInstance().currentUser?.displayName ?: ""
    }

    fun onSaveChanges(
        onMessage: (message: String) -> Unit
    ) {
        if (name.isBlank()) {
            onMessage("Fill the fields")
            return
        }

        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }
        viewModelScope.launch {
            try {
                FirebaseAuth.getInstance().currentUser!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onMessage("Information updated")
                        } else {
                            onMessage("An error ocurred")
                        }
                    }
            } catch (e: Exception) {
                onMessage("Error: ${e.message}")
            }
        }
    }

    fun onSignOut(login: () -> Unit) {
        viewModelScope.launch {
            try {
                FirebaseAuth.getInstance().signOut()
                login()

            } catch (_: Exception) {}
        }
    }
}