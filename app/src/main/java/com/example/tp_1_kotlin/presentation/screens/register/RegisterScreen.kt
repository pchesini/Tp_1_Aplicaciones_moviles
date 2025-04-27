package com.example.tp_1_kotlin.presentation.screens.register

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tp_1_kotlin.presentation.components.OutlinedInputField
import com.example.tp_1_kotlin.presentation.components.OutlinedPasswordInputField
import com.example.tp_1_kotlin.presentation.navigation.Login


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel) {
    val context = LocalContext.current

    val name = viewModel.state.name
    val email = viewModel.state.email
    val password = viewModel.state.password
    val confirmPassword = viewModel.state.confirmPassword
    val emailErrorMessage = viewModel.state.emailErrorMessage
    val nameError = viewModel.state.nameError
    val emailError1 = viewModel.state.emailError1
    val emailError2 = viewModel.state.emailError2
    val passwordError = viewModel.state.passwordError
    val confirmPasswordError = viewModel.state.confirmPasswordError
    val successfulRegister = viewModel.state.successfulRegister

    LaunchedEffect(successfulRegister) {
        if (successfulRegister) {
            viewModel.saveUserCredentials(name, email, password)
            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_LONG).show()
            navController.navigate(Login)
        }
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

            OutlinedInputField(name, "Nombre", viewModel::onNameChange, viewModel::validateFields, nameError, "El nombre no puede estar vacío")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedInputField(email, "Email", viewModel::onEmailChange, viewModel::validateFields, emailError1 || emailError2, emailErrorMessage)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedPasswordInputField(password, "Contraseña", viewModel::onPasswordChange, viewModel::validateFields, passwordError, "La contraseña debe tener al menos 6 caracteres")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedPasswordInputField(confirmPassword, "Repetir Contraseña", viewModel::onConfirmPasswordChange, viewModel::validateFields, confirmPasswordError, "Las contraseñas no coinciden")
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.validateFields() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }
        }
    }
}
