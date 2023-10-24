package com.group5.recipeapp.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.group5.recipeapp.io.FirestoreRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val firestoreRepository: FirestoreRepository = FirestoreRepository()

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
                    .addOnSuccessListener { result ->
                        val id = result.user?.uid

                        if (id != null) {
                            firestoreRepository.getUserByEmail(email)
                                .addOnSuccessListener { documents ->
                                    if (documents.isEmpty) {
                                        firestoreRepository.saveUserFromLogin(id, email)
                                    }
                                }
                        }
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
                    .addOnSuccessListener { result ->
                        val id = result.user?.uid
                        val email = result.user?.email

                        if (id != null && email != null) {
                            firestoreRepository.getUserByEmail(email)
                                .addOnSuccessListener { documents ->
                                    if (documents.isEmpty) {
                                        firestoreRepository.saveUserFromLogin(id, email)
                                    }
                                }
                        }
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
