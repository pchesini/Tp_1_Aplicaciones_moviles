package com.example.tp_1_kotlin.ui.theme.register

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Nombre
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            isError = nameError
        )
        if (nameError) Text("El nombre no puede estar vacío", color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = emailError
        )
        if (emailError) Text("El email no puede estar vacío", color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError
        )
        if (passwordError) Text("La contraseña debe tener al menos 6 caracteres", color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.height(8.dp))

        // Confirmar Contraseña
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Repetir Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = confirmPasswordError
        )
        if (confirmPasswordError) Text("Las contraseñas no coinciden", color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Registro
        Button(
            onClick = {
                nameError = name.isBlank()
                emailError = email.isBlank()
                passwordError = password.length < 6
                confirmPasswordError = password != confirmPassword

                if (!nameError && !emailError && !passwordError && !confirmPasswordError) {
                    saveUserCredentials(context,name, email, password)
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_LONG).show()
                    navController.navigate("login") // Vuelve a la pantalla de login
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }
    }
}

// Función para guardar usuario en SharedPreferences
fun saveUserCredentials(context: Context,name: String, email: String, password: String) {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("name", name)
        putString("email", email)
        putString("password", password)
        apply()
    }
}