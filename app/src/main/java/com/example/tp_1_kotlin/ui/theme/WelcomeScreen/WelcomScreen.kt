package com.example.tp_1_kotlin.ui.theme.WelcomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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

@Composable
fun WelcomeScreen(
    username: String,
    navController: NavController
) {
    var selectedPlatform by remember { mutableStateOf("Android") }
    var preferences by remember { mutableStateOf(setOf<String>()) }
    var otherPreference by remember { mutableStateOf("") }
    var showTextField by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido a la aplicación, $username!",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Seleccione su plataforma:",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row {
                RadioButton(
                    selected = selectedPlatform == "Android",
                    onClick = { selectedPlatform = "Android" }
                )
                Text("Android", color = MaterialTheme.colorScheme.onBackground)

                Spacer(modifier = Modifier.width(8.dp))

                RadioButton(
                    selected = selectedPlatform == "iOS",
                    onClick = { selectedPlatform = "iOS" }
                )
                Text("iOS", color = MaterialTheme.colorScheme.onBackground)
            }

            val logoRes = if (selectedPlatform == "Android") R.drawable.android_logo else R.drawable.ios_logo
            Image(
                painter = painterResource(id = logoRes),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Seleccione sus preferencias:",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            val options = listOf("Programación", "Redes", "Seguridad", "Hardware", "Otra")

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                options.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = preferences.contains(option),
                            onCheckedChange = { isChecked ->
                                preferences = if (isChecked) preferences + option else preferences - option
                                if (option == "Otra") showTextField = isChecked
                            }
                        )
                        Text(option, color = MaterialTheme.colorScheme.onBackground)
                    }
                }
            }

            if (showTextField) {
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = otherPreference,
                    onValueChange = { otherPreference = it },
                    label = { Text("Ingrese su preferencia") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salir", fontSize = 18.sp)
            }
        }
    }
}
