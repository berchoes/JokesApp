package com.example.jokesapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jokesapp.R
import com.example.jokesapp.presentation.common.Screen

/**
 * Created by Berk Çelik on 19.01.2022.
 */


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
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
                onClick = {
                    navController.navigate(Screen.CategoriesScreen.route)
                }
            ) {
                Text(text = "Categories", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {}
            ) {
                Text(text = "Tell a joke!", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {}
            ) {
                Text(text = "Search", fontSize = 16.sp)
            }
        }
    }
}