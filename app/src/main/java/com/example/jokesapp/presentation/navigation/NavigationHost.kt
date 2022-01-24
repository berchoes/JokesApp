package com.example.jokesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.jokesapp.presentation.categories.CategoriesScreen
import com.example.jokesapp.presentation.favorites.FavoritesScreen
import com.example.jokesapp.presentation.home.HomeScreen
import com.example.jokesapp.presentation.search.SearchScreen

/**
 * Created by berchoes on 24.01.2022.
 */

@ExperimentalCoilApi
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