package com.example.tp_1_kotlin.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp_1_kotlin.R
import com.example.tp_1_kotlin.presentation.navigation.Home
import com.example.tp_1_kotlin.presentation.navigation.Register

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    val context = LocalContext.current
    val name = viewModel.state.name
    val password = viewModel.state.password
    val errorMessage = viewModel.state.errorMessage

    fun loginUser() {
        if (viewModel.validateUserCredentials(name, password)) {
            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
            navController.navigate(Home(username = name))
        } else {
            viewModel.onErrorMessageChange("Usuario o contraseña incorrectos")
        }
    }

    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background-image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .padding(24.dp)
                .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Iniciar Sesión",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { viewModel.onNameChange(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        loginUser()
                    }
                ),
                label = { Text("Nombre") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        loginUser()
                    }
                ),
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)

            Button(
                onClick = { loginUser() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = { navController.navigate(Register) }) {
                Text("Registrarse", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}