package com.kikepb7.rickandmortyapp.ui.common.navigation

sealed class Routes (val route: String) {
    data object Home: Routes("home")

    data object Episodes: Routes("episodes")
    data object Characters: Routes("characters")
    data object Locations: Routes("locations")
}