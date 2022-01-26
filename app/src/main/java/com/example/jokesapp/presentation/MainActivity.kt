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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jokesapp.presentation.navigation.BottomNavBar
import com.example.jokesapp.presentation.navigation.NavigationHost
import com.example.jokesapp.presentation.navigation.NavigationItem
import com.example.jokesapp.theme.JokesAppTheme
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

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            bottomBar = {
                BottomNavBar(navigationItems, navController) {
                    navController.navigate(it.route){
                        popUpTo(0)
                    }
                }
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                NavigationHost(navController = navController)
            }
        }
    }
}


