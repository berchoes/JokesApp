package com.example.jokesapp.presentation.navigation

import com.example.jokesapp.R

/**
 * Created by berchoes on 20.01.2022.
 */


sealed class NavigationItem(val route: String, val title: String, val icon: Int) {
    object Home : NavigationItem("home", "Home", R.drawable.ic_home)
    object Favorites : NavigationItem("favorites", "Favorites", R.drawable.ic_favorites)
}