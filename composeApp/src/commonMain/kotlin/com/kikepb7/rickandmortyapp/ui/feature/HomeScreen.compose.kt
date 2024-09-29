package com.kikepb7.rickandmortyapp.ui.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.BottomBarItem
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.BottomBarItem.Characters
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.BottomBarItem.Episodes
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.BottomBarItem.Locations
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.NavigationBottomWrapper

@Composable
fun HomeScreenView() {
    val items = listOf(Episodes(), Characters(), Locations())
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigation(items = items, navController = navController) }) { padding ->
        Box(
            modifier = Modifier.padding(paddingValues = padding)
        ) {
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