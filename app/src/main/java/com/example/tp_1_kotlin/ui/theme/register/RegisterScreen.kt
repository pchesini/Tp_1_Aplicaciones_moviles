package com.example.tp_1_kotlin.ui.theme.register

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var emailError1 by remember { mutableStateOf(false) }
    var emailError2 by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    fun validateFields():Boolean{
        nameError = name.isBlank()
        emailError1 = email.isBlank()
        emailError2 = !email.matches(Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+\$"))
        passwordError = password.length < 6
        confirmPasswordError = password != confirmPassword
        return !nameError && !emailError1 && !emailError2 && !passwordError && !confirmPasswordError
    }

    fun succesfulRegister(){
        saveUserCredentials(context, name, email, password)
        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_LONG).show()
        navController.navigate("login")
    }

    Scaffold(
        modifier = Modifier
            .imePadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Registro") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = {
                    if (it.contains("\n")) {
                        name = it.replace("\n", "")
                        if(validateFields()){
                            succesfulRegister()
                        }
                    } else {
                        name = it
                    }
                },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                isError = nameError,
                maxLines = 1
            )

            if (nameError) {
                Text(
                    "El nombre no puede estar vacío",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    if (it.contains("\n")) {
                        email = it.replace("\n", "")
                        if(validateFields()){
                            succesfulRegister()
                        }
                    } else {
                        email = it
                    }
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                isError = emailError1 || emailError2,
                maxLines = 1
            )

            if (emailError1) {
                Text(
                    "El email no puede estar vacío",
                    color = MaterialTheme.colorScheme.error
                )
            }

            if (emailError2 && !emailError1) {
                Text(
                    "El email debe tener el formato: usuario@ejemplo.com",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    if (it.contains("\n")) {
                        password = it.replace("\n", "")
                        if(validateFields()){
                            succesfulRegister()
                        }
                    } else {
                        password = it
                    }
                },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = passwordError,
                maxLines = 1
            )

            if (passwordError) {
                Text(
                    "La contraseña debe tener al menos 6 caracteres",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    if (it.contains("\n")) {
                        confirmPassword = it.replace("\n", "")
                        if(validateFields()){
                            succesfulRegister()
                        }
                    } else {
                        confirmPassword = it
                    }
                },
                label = { Text("Repetir Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = confirmPasswordError,
                maxLines = 1
            )

            if (confirmPasswordError) {
                Text(
                    "Las contraseñas no coinciden",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (validateFields()) {
                        succesfulRegister()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }
        }
    }
}

fun saveUserCredentials(context: Context,name: String, email: String, password: String) {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("name", name)
        putString("email", email)
        putString("password", password)
        apply()
    }
}

