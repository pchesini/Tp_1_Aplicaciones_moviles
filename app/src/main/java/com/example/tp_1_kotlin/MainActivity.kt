package com.example.tp_1_kotlin

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.tp_1_kotlin.data.UserRepository
import com.example.tp_1_kotlin.presentation.navigation.NavGraph
import com.example.tp_1_kotlin.presentation.theme.Tp_1_kotlinTheme
import com.example.tp_1_kotlin.presentation.screens.home.HomeViewModel
import com.example.tp_1_kotlin.presentation.screens.login.LoginViewModel
import com.example.tp_1_kotlin.presentation.screens.register.RegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val repository = UserRepository(sharedPreferences)
        val viewModelFactory = AppViewModelFactory(repository)
        setContent {
            Tp_1_kotlinTheme {
                val navController = rememberNavController()
                NavGraph(navController, viewModelFactory)
            }
        }
    }
}

class AppViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
