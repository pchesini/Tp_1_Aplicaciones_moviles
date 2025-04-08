package com.example.tp_1_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.tp_1_kotlin.navigation.NavGraph
import com.example.tp_1_kotlin.ui.theme.Tp_1_kotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp_1_kotlinTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}