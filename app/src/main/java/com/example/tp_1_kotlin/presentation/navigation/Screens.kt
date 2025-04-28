package com.example.tp_1_kotlin.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data class Home(val username : String)

@Serializable
object Register

@Serializable
object Login