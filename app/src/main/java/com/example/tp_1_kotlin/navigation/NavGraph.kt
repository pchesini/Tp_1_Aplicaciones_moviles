package com.example.tp_1_kotlin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tp_1_kotlin.ui.theme.login.LoginScreen
import com.example.tp_1_kotlin.ui.theme.register.RegisterScreen
import com.example.tp_1_kotlin.ui.theme.WelcomeScreen.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("welcome/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Usuario"
            WelcomeScreen(username, navController)
        }
    }
}