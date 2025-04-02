package com.example.tp_1_kotlin.ui.theme.WelcomeScreen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp_1_kotlin.R

@Composable
fun WelcomeScreen(username: String) {
    val context = LocalContext.current

    var selectedPlatform by remember { mutableStateOf("Android") }
    var preferences by remember { mutableStateOf(setOf<String>()) }
    var otherPreference by remember { mutableStateOf("") }
    var showTextField by remember { mutableStateOf(false) } // Para mostrar u ocultar la casilla

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a la aplicación, $username!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selección de plataforma
        Text("Seleccione su plataforma:")
        Row {
            RadioButton(
                selected = selectedPlatform == "Android",
                onClick = { selectedPlatform = "Android" }
            )
            Text("Android")
            RadioButton(
                selected = selectedPlatform == "iOS",
                onClick = { selectedPlatform = "iOS" }
            )
            Text("iOS")
        }

        val logoRes = if (selectedPlatform == "Android") R.drawable.android_logo else R.drawable.ios_logo
        Image(
            painter = painterResource(id = logoRes),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selección de preferencias
        Text(
            text = "Seleccione sus preferencias:",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center // Centrar el título
        )

        Spacer(modifier = Modifier.height(8.dp))

        val options = listOf("Programación", "Redes", "Seguridad", "Hardware", "Otra")

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start // Alinear las opciones a la izquierda
        ) {
            options.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = preferences.contains(option),
                        onCheckedChange = { isChecked ->
                            preferences = if (isChecked) preferences + option else preferences - option
                            if (option == "Otra") showTextField = isChecked // Muestra la casilla solo si "Otra" está seleccionada
                        }
                    )
                    Text(option)
                }
            }
        }

        // Campo de texto si se selecciona "Otra"
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

        // 📌 Botón para salir de la app (Ahora está después de las opciones)
        Button(
            onClick = { (context as? Activity)?.finish() }, // Conversión segura
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salir", fontSize = 18.sp, color = MaterialTheme.colorScheme.onError)
        }
    }
}