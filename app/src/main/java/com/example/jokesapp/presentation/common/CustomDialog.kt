package com.example.jokesapp.presentation.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Created by berchoes on 20.01.2022.
 */


@Composable
fun CustomDialog(
    text: String? = null,
    dismissButtonText: String,
    error: String? = null,
    onDismissed: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        dismissButton = {
            Button(onClick = onDismissed) {
                Text(text = dismissButtonText)
            }
        },
        confirmButton = {},
        text = {

            Text(
                text = error ?: text!!,
                color = if (error != null) Color.Red else Color.White
            )
        }
    )
}