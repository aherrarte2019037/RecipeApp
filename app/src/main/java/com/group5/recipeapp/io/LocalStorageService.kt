package com.group5.recipeapp.io

import android.content.Context

//Define una clase llamada LocalStorageService que proporciona métodos para almacenar
//y recuperar datos en el almacenamiento compartido (SharedPreferences) de Android.
//Este almacenamiento es persistente y se utiliza para almacenar pequeñas cantidades
//de datos clave-valor de manera sencilla.


class LocalStorageService(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("RecipeAppPreferences", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun saveBool(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBool(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}