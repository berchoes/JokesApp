package com.example.jokesapp.presentation.favorites.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.jokesapp.R
import com.example.jokesapp.data.local.entity.FavoriteJoke

/**
 * Created by berchoes on 22.01.2022.
 */

@ExperimentalCoilApi
@Composable
fun FavoritesListItem(
    joke: FavoriteJoke,
    onDeleteClicked: (FavoriteJoke) -> Unit
) {

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .weight(1f),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = rememberImagePainter(joke.icon),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(joke.content, style = MaterialTheme.typography.body2)
        }

        Image(
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = "deleteIcon",
            modifier = Modifier
                .clickable {
                    onDeleteClicked(joke)
                }
                .padding(end = 12.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.error)
        )
    }
    Divider()
}