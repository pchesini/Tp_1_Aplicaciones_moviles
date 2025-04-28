package com.example.tp_1_kotlin.data

import android.content.SharedPreferences

class UserRepository(private val sharedPreferences: SharedPreferences) {

    fun getUsername(): String? {
        return sharedPreferences.getString("name", null)
    }

    fun getPassword(): String? {
        return sharedPreferences.getString("password", null)
    }

    fun saveUserCredentials(name : String, email : String, password : String) {
        with(sharedPreferences.edit()) {
            putString("name", name)
            putString("email", email)
            putString("password", password)
            apply()
        }
    }
}