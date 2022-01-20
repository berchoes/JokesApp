package com.example.jokesapp.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by berchoes on 20.01.2022.
 */


@Composable
fun FavoritesScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Favorites screen", modifier = Modifier.align(Alignment.Center))
    }
}