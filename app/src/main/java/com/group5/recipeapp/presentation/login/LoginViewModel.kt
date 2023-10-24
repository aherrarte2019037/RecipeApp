package com.group5.recipeapp.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit,
        onError: (message: String) -> Unit
    ) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        home()
                    }
                    .addOnFailureListener { ex ->
                        ex.localizedMessage?.let { onError(it) }
                    }
            } catch (e: Exception) {
                onError("Error: ${e.message}")
            }
        }
}
