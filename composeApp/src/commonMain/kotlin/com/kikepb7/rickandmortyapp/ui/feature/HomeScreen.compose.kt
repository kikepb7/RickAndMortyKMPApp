package com.kikepb7.rickandmortyapp.ui.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kikepb7.rickandmortyapp.ui.core.navigation.bottomnavigation.BottomBarItem
import com.kikepb7.rickandmortyapp.ui.core.navigation.bottomnavigation.BottomBarItem.Characters
import com.kikepb7.rickandmortyapp.ui.core.navigation.bottomnavigation.BottomBarItem.Episodes
import com.kikepb7.rickandmortyapp.ui.core.navigation.bottomnavigation.NavigationBottomWrapper

@Composable
fun HomeScreenView() {
    val items = listOf(Episodes(), Characters())
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigation(items = items, navController = navController) }) {
        Box {
            NavigationBottomWrapper(navController = navController)
        }
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = { Text(text = item.title) },
                onClick = {
                    navController.navigate(route = item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route = route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            )
        }
    }
}