package com.group5.recipeapp.model

data class User(
    val userId: String?,
    val email: String
) {
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "userId" to this.userId,
            "email" to this.email,
        )
    }
}
