package com.example.tp_1_kotlin.ui.theme.WelcomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tp_1_kotlin.R

@Composable
fun WelcomeScreen(username: String) {
    var selectedPlatform by remember { mutableStateOf("Android") }
    var preferences by remember { mutableStateOf(setOf<String>()) }
    var otherPreference by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Bienvenido a la aplicación, $username!", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))
        Text("Seleccione su plataforma:")
        Row {
            RadioButton(selected = selectedPlatform == "Android", onClick = { selectedPlatform = "Android" })
            Text("Android")
            RadioButton(selected = selectedPlatform == "iOS", onClick = { selectedPlatform = "iOS" })
            Text("iOS")
        }
        val logoRes = if (selectedPlatform == "Android") R.drawable.android_logo else R.drawable.ios_logo
        Image(painter = painterResource(id = logoRes), contentDescription = "Logo")

        Spacer(modifier = Modifier.height(16.dp))
        Text("Seleccione sus preferencias:")
        val options = listOf("Programación", "Redes", "Seguridad", "Hardware", "Otra")
        options.forEach { option ->
            Row {
                Checkbox(
                    checked = preferences.contains(option),
                    onCheckedChange = { isChecked ->
                        preferences = if (isChecked) preferences + option else preferences - option
                    }
                )
                Text(option)
            }
        }

        if (preferences.contains("Otra")) {
            TextField(value = otherPreference, onValueChange = { otherPreference = it }, label = { Text("Ingrese su preferencia") })
        }
    }
}