package com.example.jokesapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.R
import com.example.jokesapp.presentation.common.CustomDialog

/**
 * Created by Berk Çelik on 19.01.2022.
 */


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val dialogState = viewModel.dialogState

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.chuck_icon),
                "chuckNorrisImage",
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                ) ,
                onClick = {
                    viewModel.getRandomJoke()
                }
            ) {
                Text(text = "Tell a joke!", fontSize = 16.sp)
            }
        }

        if (dialogState.isShowing) {
            CustomDialog(
                joke = dialogState.joke,
                dismissButtonText = "OKAY",
                error = dialogState.errorMessage,
                onFavoriteClicked = { isFav, joke ->
                    if (isFav) viewModel.insertFavorite(joke) else viewModel.deleteFavorite(joke)
                }
            ) {
                viewModel.dismissDialog()
            }
        }

        if (dialogState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}