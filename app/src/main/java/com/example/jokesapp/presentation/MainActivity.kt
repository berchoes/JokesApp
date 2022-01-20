package com.example.jokesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jokesapp.presentation.categories.CategoriesScreen
import com.example.jokesapp.presentation.common.Screen
import com.example.jokesapp.presentation.favorites.FavoritesScreen
import com.example.jokesapp.presentation.home.HomeScreen
import com.example.jokesapp.presentation.navigation.BottomNavBar
import com.example.jokesapp.presentation.navigation.NavigationItem
import com.example.jokesapp.util.theme.JokesAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokesAppTheme {
                MainPage()
            }
        }
    }


    @Composable
    fun MainPage() {
        val navigationItems = listOf(NavigationItem.Home, NavigationItem.Favorites)
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState() // todo will be used for showing a snackbar.

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            bottomBar = {
                BottomNavBar(navigationItems, navController) {
                    navController.navigate(it.route)
                }

            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                NavigationHost(navController = navController)
            }
        }
    }

    @Composable
    fun NavigationHost(navController: NavHostController) {
        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(
                route = Screen.Home.route
            ) {
                HomeScreen(navController)
            }

            composable(
                route = Screen.CategoriesScreen.route
            ) {
                CategoriesScreen()
            }

            composable(
                route = Screen.Favorites.route
            ) {
                FavoritesScreen()
            }


        }
    }
}


