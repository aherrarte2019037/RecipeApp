package com.group5.recipeapp.io

import com.google.firebase.firestore.FirebaseFirestore
import com.group5.recipeapp.model.User

class FirestoreRepository {
    fun saveUserFromLogin(id: String, email: String) {
        val user = User(
            id,
            email,
        ).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }
}