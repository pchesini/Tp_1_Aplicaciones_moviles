package com.example.tp_1_kotlin.presentation.screens.register

data class RegisterState(
    val name : String = "",
    val email : String = "",
    val password : String = "",
    val confirmPassword : String = "",
    val emailErrorMessage : String = "",
    val nameError : Boolean = false,
    val emailError1 : Boolean = false,
    val emailError2 : Boolean = false,
    val passwordError : Boolean = false,
    val confirmPasswordError : Boolean = false,
    val successfulRegister : Boolean = false
)
