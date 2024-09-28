package com.kikepb7.rickandmortyapp.di

import com.kikepb7.rickandmortyapp.ui.feature.characters.CharactersViewModel
import com.kikepb7.rickandmortyapp.ui.feature.episodes.EpisodesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
}