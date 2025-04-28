package com.example.tp_1_kotlin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.tp_1_kotlin.AppViewModelFactory
import com.example.tp_1_kotlin.presentation.screens.home.HomeScreen
import com.example.tp_1_kotlin.presentation.screens.home.HomeViewModel
import com.example.tp_1_kotlin.presentation.screens.login.LoginScreen
import com.example.tp_1_kotlin.presentation.screens.login.LoginViewModel
import com.example.tp_1_kotlin.presentation.screens.register.RegisterScreen
import com.example.tp_1_kotlin.presentation.screens.register.RegisterViewModel

@Composable
fun NavGraph(navController: NavHostController, viewModelFactory: AppViewModelFactory) {
    NavHost(navController, startDestination = Login) {
        composable<Login> {
            val loginViewModel = viewModel<LoginViewModel>(factory = viewModelFactory)
            LoginScreen(navController, loginViewModel)
        }
        composable<Register> {
            val registerViewModel = viewModel<RegisterViewModel>(factory = viewModelFactory)
            RegisterScreen(navController, registerViewModel)
        }
        composable<Home> {
            val homeViewModel = viewModel<HomeViewModel>(factory = viewModelFactory)
            val home : Home = it.toRoute()
            HomeScreen(home.username, navController, homeViewModel)
        }
    }
}