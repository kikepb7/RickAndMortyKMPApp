package com.kikepb7.rickandmortyapp.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kikepb7.rickandmortyapp.ui.core.navigation.Routes
import com.kikepb7.rickandmortyapp.ui.home.characters.CharactersScreen
import com.kikepb7.rickandmortyapp.ui.home.episodes.EpisodesScreen

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
    }
}