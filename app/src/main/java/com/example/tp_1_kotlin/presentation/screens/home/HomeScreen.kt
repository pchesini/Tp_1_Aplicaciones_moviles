package com.example.tp_1_kotlin.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tp_1_kotlin.R
import com.example.tp_1_kotlin.presentation.navigation.Login

@Composable
fun HomeScreen(
    username: String,
    navController: NavController,
    viewModel: HomeViewModel
) {
    val selectedPlatform = viewModel.state.selectedPlatform
    val preferences = viewModel.state.preferences
    val otherPreference = viewModel.state.otherPreference
    val showTextField = viewModel.state.showTextField
    val options = listOf("Programación", "Redes", "Seguridad", "Hardware", "Otra")
    val logoRes = if (selectedPlatform == "Android") R.drawable.android_logo else R.drawable.ios_logo

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bienvenido a la aplicación,\n$username!",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        "Seleccione su plataforma:",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedPlatform == "Android",
                            onClick = { viewModel.onSelectedPlatformChange("Android") }
                        )
                        Text("Android", color = MaterialTheme.colorScheme.onSurface)

                        Spacer(modifier = Modifier.width(12.dp))

                        RadioButton(
                            selected = selectedPlatform == "iOS",
                            onClick = { viewModel.onSelectedPlatformChange("iOS") }
                        )
                        Text("iOS", color = MaterialTheme.colorScheme.onSurface)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Image(
                        painter = painterResource(id = logoRes),
                        contentDescription = "Logo",
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Seleccione sus preferencias:",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        options.forEach { option ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = preferences.contains(option),
                                    onCheckedChange = { isChecked ->
                                        viewModel.onPreferenceCheckChange(isChecked, option)
                                        if (option == "Otra") viewModel.onShowTextFieldChange(isChecked)
                                    }
                                )
                                Text(option, color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    }

                    if (showTextField) {
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = otherPreference,
                            onValueChange = { viewModel.onOtherPreferenceChange(it) },
                            label = { Text("Ingrese su preferencia") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            navController.navigate(Login) {
                                popUpTo(Login) { inclusive = true }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Salir", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}