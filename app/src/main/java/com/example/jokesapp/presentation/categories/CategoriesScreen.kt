package com.example.jokesapp.presentation.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.jokesapp.presentation.categories.components.CategoryListItem
import com.example.jokesapp.presentation.common.CustomDialog

/**
 * Created by berchoes on 20.01.2022.
 */


@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val screenState = viewModel.screenState
    val dialogState = viewModel.dialogState

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(screenState.categories) { category ->
                CategoryListItem(
                    onClick = {
                        viewModel.getRandomJoke(it)
                    },
                    category = category
                )
            }
        }

        if (screenState.error.isNotBlank()) {
            Text(
                text = screenState.error,
                textAlign = TextAlign.Center,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center)
            )
        }

        if (screenState.isLoading || dialogState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (dialogState.isShowing) {
            CustomDialog(
                joke = dialogState.joke,
                "OKAY",
                error = dialogState.errorMessage,
                onFavoriteClicked = { isFavorite, joke ->
                    if (isFavorite) {
                        viewModel.insertFavorite(joke)
                    } else {
                        viewModel.deleteFavorite(joke)
                    }
                },
                onDismissed = {
                    viewModel.dismissDialog()
                })
        }
    }
}