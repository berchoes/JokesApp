package com.example.jokesapp.presentation.navigation

/**
 * Created by berchoes on 20.01.2022.
 */


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

/**
 * Created by Berk Ç. on 15.12.2021.
 */

@Composable
fun BottomNavBar(
    items: List<NavigationItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (NavigationItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.White,
        elevation = 8.dp
    ) {
        items.forEach { item ->
            val isSelected = item.route == backStackEntry.value?.destination?.route

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                selected = isSelected,
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.White,
                onClick = { onItemClick(item) }
            )
        }
    }
}