package com.example.tp_1_kotlin.ui.theme.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = email, onValueChange = { email = it }, label = { Text("E-mail") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Repetir Contraseña") })
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = errorMessage, color = MaterialTheme.colorScheme.error)

        Button(onClick = {
            if (name.isEmpty() || email.isEmpty()) {
                errorMessage = "El nombre y el e-mail son obligatorios"
            } else if (password.length < 6) {
                errorMessage = "La contraseña debe tener al menos 6 caracteres"
            } else if (password != confirmPassword) {
                errorMessage = "Las contraseñas no coinciden"
            } else {
                navController.navigate("login")
            }
        }) {
            Text("Registrarse")
        }
    }
}