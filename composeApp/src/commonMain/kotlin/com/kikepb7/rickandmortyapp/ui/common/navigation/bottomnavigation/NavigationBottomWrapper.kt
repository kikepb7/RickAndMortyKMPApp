package com.kikepb7.rickandmortyapp.ui.common.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kikepb7.rickandmortyapp.ui.common.navigation.CharacterDetail
import com.kikepb7.rickandmortyapp.ui.common.navigation.Routes
import com.kikepb7.rickandmortyapp.ui.feature.characters.CharactersScreen
import com.kikepb7.rickandmortyapp.ui.feature.episodes.EpisodesScreen
import com.kikepb7.rickandmortyapp.ui.feature.locations.LocationsScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun NavigationBottomWrapper(
    navController: NavHostController,
    mainNavController: NavHostController
) {
    NavHost(navController = navController, startDestination = Routes.Episodes.route) {
        composable(route = Routes.Episodes.route) {
            EpisodesScreen()
        }

        composable(route = Routes.Characters.route) {
            CharactersScreen(
                navigateToDetail = { characterModel ->
                    val encode = Json.encodeToString(value = characterModel)
                    mainNavController.navigate(route = CharacterDetail(characterModel = encode))
                }
            )
        }

        composable(route = Routes.Locations.route) {
            LocationsScreen()
        }
    }
}