package com.example.jokesapp.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.jokesapp.domain.model.Joke

/**
 * Created by berchoes on 21.01.2022.
 */

@ExperimentalCoilApi
@Composable
fun SearchListItem(
    joke: Joke,
    onFavoriteClicked: (Boolean, Joke) -> Unit
) {

    val isFavorite = remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(12.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier, verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = rememberImagePainter(joke.iconUrl),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(joke.content, style = MaterialTheme.typography.body2)
        }

        IconButton(onClick = {
            onFavoriteClicked(isFavorite.value,joke)
        }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = if (isFavorite.value) MaterialTheme.colors.primary else Color.Gray
            )
        }

    }

    Divider()
}