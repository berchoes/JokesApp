package com.example.jokesapp.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.jokesapp.presentation.search.components.SearchBar
import com.example.jokesapp.presentation.search.components.SearchListItem

/**
 * Created by berchoes on 20.01.2022.
 */

@ExperimentalCoilApi
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(
                text = viewModel.searchText.value,
                onCloseClicked = { viewModel.setSearchText("") },
                onTextChange = { viewModel.setSearchText(it) },
                onSearchClicked = { viewModel.searchJokes(it) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(state.jokes) {
                    SearchListItem(joke = it, onLongPress = {
                        viewModel.insertFavorite(it)
                    })
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (state.isEmptyResult) {
            Text(
                text = "No jokes found :(",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.errorMessage != null) {
            Text(
                text = state.errorMessage,
                textAlign = TextAlign.Center,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center)
            )
        }
    }
}