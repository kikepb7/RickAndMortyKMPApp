package com.kikepb7.rickandmortyapp

import androidx.compose.ui.window.ComposeUIViewController
import com.kikepb7.rickandmortyapp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }