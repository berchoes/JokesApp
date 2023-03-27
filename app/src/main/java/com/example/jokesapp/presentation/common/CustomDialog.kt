package com.example.jokesapp.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.jokesapp.domain.model.Joke

/**
 * Created by berchoes on 20.01.2022.
 */


@Composable
fun CustomDialog(
    joke: Joke? = null,
    dismissButtonText: String,
    error: String? = null,
    onFavoriteClicked: (Boolean, Joke) -> Unit,
    onDismissed: () -> Unit,
) {
    val isFavorite = rememberSaveable { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = {},
        dismissButton = {
            if (error == null) {
                IconButton(onClick = {
                    isFavorite.value = !isFavorite.value
                    joke?.let {
                        onFavoriteClicked(isFavorite.value, it)
                    }
                }) {
                    Icon(
                        modifier = Modifier.padding(bottom = 14.dp),
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "favIcon",
                        tint = if (isFavorite.value) MaterialTheme.colors.error else Color.Gray
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismissed) {
                Text(text = dismissButtonText)
            }
        },
        text = {
            Text(
                text = error ?: joke?.content!!,
                color = if (error != null) Color.Red else Color.White
            )
        }
    )
}