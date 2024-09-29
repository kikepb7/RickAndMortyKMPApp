package com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.kikepb7.rickandmortyapp.ui.common.navigation.Routes

// Contract that needs to have a bottom bar item
sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, null)
        }
    ) : BottomBarItem()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Person, null)
        }
    ) : BottomBarItem()

    data class Locations(
        override val route: String = Routes.Locations.route,
        override val title: String = "Locations",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.LocationOn, null)
        }
    ) : BottomBarItem()
}