package com.example.jokesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jokesapp.presentation.categories.CategoriesScreen
import com.example.jokesapp.presentation.favorites.FavoritesScreen
import com.example.jokesapp.presentation.home.HomeScreen
import com.example.jokesapp.presentation.navigation.BottomNavBar
import com.example.jokesapp.presentation.navigation.NavigationItem
import com.example.jokesapp.presentation.search.SearchScreen
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
        val navigationItems = listOf(
            NavigationItem.Home,
            NavigationItem.Categories,
            NavigationItem.Search,
            NavigationItem.Favorites
        )
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
        NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
            composable(
                route = NavigationItem.Home.route
            ) {
                HomeScreen()
            }

            composable(
                route = NavigationItem.Categories.route
            ) {
                CategoriesScreen()
            }

            composable(
                route = NavigationItem.Favorites.route
            ) {
                FavoritesScreen()
            }

            composable(
                route = NavigationItem.Search.route
            ) {
                SearchScreen()
            }


        }
    }
}


