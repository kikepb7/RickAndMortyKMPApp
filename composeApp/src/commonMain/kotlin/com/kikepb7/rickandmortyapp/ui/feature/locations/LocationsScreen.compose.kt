package com.kikepb7.rickandmortyapp.ui.feature.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LocationsScreen() {
    val locationsViewModel = koinViewModel<LocationsViewModel>()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    )
}