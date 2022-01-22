package com.example.jokesapp.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.jokesapp.presentation.favorites.components.FavoritesListItem

/**
 * Created by berchoes on 20.01.2022.
 */

@ExperimentalCoilApi
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favorites = viewModel.favorites.value

    Box(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(favorites) {
                FavoritesListItem(joke = it, onDeleteClicked = { favJoke ->
                    viewModel.deleteFavorite(favJoke)
                })
            }
        }

        if(favorites.isEmpty()){
            Text(
                text = "Do you really think that I'm not funny?",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center)
            )
        }
    }
}