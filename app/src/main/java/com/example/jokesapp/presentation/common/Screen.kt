package com.example.jokesapp.presentation.common

/**
 * Created by berchoes on 20.01.2022.
 */
sealed class Screen(val route: String){
    object CategoriesScreen: Screen("categories_screen")
    object SearchScreen: Screen("search_screen")
    object Home: Screen("home")
    object Favorites: Screen("favorites")
}
