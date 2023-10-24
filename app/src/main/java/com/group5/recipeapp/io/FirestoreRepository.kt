package com.group5.recipeapp.io

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.group5.recipeapp.model.User

class FirestoreRepository {
    private val instance = FirebaseFirestore.getInstance()

    fun saveUserFromLogin(userId: String, email: String) {
        val user = User(
            userId,
            email,
        ).toMap()

       instance.collection("users")
            .add(user)
    }

    fun getUserByEmail(email: String): Task<QuerySnapshot> {
        return instance.collection("users")
            .whereEqualTo("email", email)
            .get()
    }

}