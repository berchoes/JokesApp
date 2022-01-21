package com.example.jokesapp.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    onLongPress: (Joke) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), verticalAlignment = Alignment.Top) {
            Image(
                painter = rememberImagePainter(joke.iconUrl),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(joke.content, style = MaterialTheme.typography.body2)
        }
        Divider()
    }
}