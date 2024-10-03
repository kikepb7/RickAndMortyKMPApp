package com.kikepb7.rickandmortyapp.ui.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.BottomBarItem
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.BottomBarItem.Characters
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.BottomBarItem.Episodes
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.BottomBarItem.Locations
import com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation.NavigationBottomWrapper
import com.kikepb7.rickandmortyapp.ui.theme.BackgroundSecondaryColor
import com.kikepb7.rickandmortyapp.ui.theme.BackgroundTertiaryColor
import com.kikepb7.rickandmortyapp.ui.theme.DefaultTextColor
import com.kikepb7.rickandmortyapp.ui.theme.Green
import org.jetbrains.compose.resources.painterResource
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.rickandmorty_logo

@Composable
fun HomeScreenView(
    mainNavController: NavHostController
) {
    val items = listOf(Episodes(), Characters(), Locations())
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNavigation(
                items = items,
                navController = navController
            )
        }) { padding ->
        Box(
            modifier = Modifier.padding(paddingValues = padding)
        ) {
            NavigationBottomWrapper(
                navController = navController,
                mainNavController = mainNavController
            )
        }
    }
}

@Composable
fun TopBar() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = BackgroundSecondaryColor),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.rickandmorty_logo),
            contentDescription = "Rick and Morty Toolbar",
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = BackgroundSecondaryColor,
        contentColor = Green
    ) {
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Green,
                    selectedIconColor = BackgroundTertiaryColor,
                    unselectedIconColor = Green
                ),
                icon = item.icon,
                label = {
                    Text(
                        text = item.title,
                        color = DefaultTextColor
                    )
                },
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