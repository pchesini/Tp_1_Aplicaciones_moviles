package com.example.tp_1_kotlin.presentation.screens.home

data class HomeState(
    val selectedPlatform : String = "Android",
    val preferences : Set<String> = emptySet(),
    val otherPreference : String = "",
    val showTextField : Boolean = false
)
