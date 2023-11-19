package com.group5.recipeapp.io

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.group5.recipeapp.model.User

//FirestoreRepository, que actúa como un repositorio para interactuar con Firebase Firestore,
//un servicio de base de datos en la nube proporcionado por Firebase.


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