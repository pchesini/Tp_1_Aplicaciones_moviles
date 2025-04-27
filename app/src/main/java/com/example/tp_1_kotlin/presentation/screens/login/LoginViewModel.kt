package com.example.tp_1_kotlin.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tp_1_kotlin.data.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onNameChange(name : String) {
        state = state.copy(name = name)
    }

    fun onPasswordChange(password : String) {
        state = state.copy(password = password)
    }

    fun onErrorMessageChange(errorMessage : String) {
        state = state.copy(errorMessage = errorMessage)
    }

    fun validateUserCredentials(name: String, password: String): Boolean {
        if (name == "Juan Torres" && password == "1234utn") {
            return true
        }

        val userName = userRepository.getUsername()
        val userPassword = userRepository.getPassword()

        return userName == name && userPassword == password
    }
}