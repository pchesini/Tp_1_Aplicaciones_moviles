package com.example.tp_1_kotlin.presentation.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tp_1_kotlin.data.UserRepository

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    fun onNameChange(name : String) {
        state = state.copy(name = name)
    }

    fun onEmailChange(email : String) {
        state = state.copy(email = email)
    }

    fun onPasswordChange(password : String) {
        state = state.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword : String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun validateFields() {
        state = state.copy(
            nameError = state.name.isBlank(),
            emailError1 = state.email.isBlank(),
            emailError2 = !state.email.matches(Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+\$")),
            passwordError = state.password.length < 6,
            confirmPasswordError = state.password != state.confirmPassword,
        )

        if (state.emailError1) {
            state = state.copy(emailErrorMessage = "El email no puede estar vacío")
        }

        if (state.emailError2 && !state.emailError1) {
            state = state.copy(emailErrorMessage = "El email debe tener el formato: usuario@ejemplo.com")
        }

        state = state.copy(
            successfulRegister = (!state.nameError && !state.emailError1 && !state.emailError2 && !state.passwordError && !state.confirmPasswordError)
        )
    }

    fun saveUserCredentials(name : String, username : String, password : String) {
        repository.saveUserCredentials(name, username, password)
    }
}