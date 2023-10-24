package com.group5.recipeapp.presentation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
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
    ) {
        if (email.isBlank() || password.isBlank()) {
            onError("Please fill in all the fields")
            return
        }
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

    fun signInWithGoogle(
        credentials: AuthCredential,
        home: () -> Unit,
        onError: (message: String) -> Unit
    ) =
        viewModelScope.launch {
            try {
                auth.signInWithCredential(credentials)
                    .addOnSuccessListener {
                        home()
                    }
                    .addOnFailureListener { ex ->
                        Log.d("Google Error", ex.toString())
                        ex.localizedMessage?.let { onError(it) }
                    }
            } catch (e: Exception) {
                Log.d("Google Error", e.toString())
                onError("Error: ${e.message}")
            }
        }
}
