package com.group5.recipeapp.model

data class User(
    val id: String?,
    val email: String
) {
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "id" to this.id,
            "email" to this.email,
        )
    }
}
