package com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kikepb7.rickandmortyapp.ui.common.navigation.Routes
import com.kikepb7.rickandmortyapp.ui.feature.characters.CharactersScreen
import com.kikepb7.rickandmortyapp.ui.feature.episodes.EpisodesScreen
import com.kikepb7.rickandmortyapp.ui.feature.locations.LocationsScreen

@Composable
fun NavigationBottomWrapper(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Routes.Episodes.route) {
        composable(route = Routes.Episodes.route) {
            EpisodesScreen()
        }

        composable(route = Routes.Characters.route) {
            CharactersScreen()
        }

        composable(route = Routes.Locations.route) {
            LocationsScreen()
        }
    }
}