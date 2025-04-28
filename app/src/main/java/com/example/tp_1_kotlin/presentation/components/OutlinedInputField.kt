package com.example.tp_1_kotlin.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun OutlinedInputField( value : String, label : String, onValueChange : (String) -> Unit, onDone : () -> Unit, isError : Boolean, errorMessage : String) {
    OutlinedTextField(
        value = value,
        onValueChange =  onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ),
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        isError = isError,
        maxLines = 1
    )
    if (isError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error
        )
    }
}