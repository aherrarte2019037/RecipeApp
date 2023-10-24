package com.group5.recipeapp.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.group5.recipeapp.io.FirestoreRepository
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val firestoreRepository: FirestoreRepository = FirestoreRepository()

    fun signUp(
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
                firestoreRepository.getUserByEmail(email)
                    .addOnSuccessListener { documents ->
                        if (!documents.isEmpty) {
                            onError("User already registered")
                        } else {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnSuccessListener { result ->
                                    result.user?.let { firestoreRepository.saveUserFromLogin(it.uid, email) }
                                    home()
                                }


                        }
                    }
            } catch (e: Exception) {
                onError("Error: ${e.message}")
            }
        }
    }
}