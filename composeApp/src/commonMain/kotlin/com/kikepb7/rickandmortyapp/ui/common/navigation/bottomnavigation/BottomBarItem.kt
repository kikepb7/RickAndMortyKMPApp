package com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kikepb7.rickandmortyapp.ui.common.navigation.Routes
import org.jetbrains.compose.resources.painterResource
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.ic_characters
import rickandmortyapp.composeapp.generated.resources.ic_episodes
import rickandmortyapp.composeapp.generated.resources.ic_location

// Contract that needs to have a bottom bar item
sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.ic_episodes),
                contentDescription = "Episodes icon",
                modifier = Modifier.size(32.dp)
            )
        }
    ) : BottomBarItem()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.ic_characters),
                contentDescription = "Characters icon",
                modifier = Modifier.size(32.dp),
            )
        }
    ) : BottomBarItem()

    data class Locations(
        override val route: String = Routes.Locations.route,
        override val title: String = "Locations",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.ic_location),
                contentDescription = "Locations icon",
                modifier = Modifier.size(32.dp)
            )
        }
    ) : BottomBarItem()
}