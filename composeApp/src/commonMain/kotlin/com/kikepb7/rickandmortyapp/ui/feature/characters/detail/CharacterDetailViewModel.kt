package com.kikepb7.rickandmortyapp.ui.feature.characters.detail

import androidx.lifecycle.ViewModel
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class CharacterDetailViewModel(
    characterModel: CharacterModel
): ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState(characterModel = characterModel))
    val state = _state.asStateFlow()

}

data class CharacterDetailState(
    val characterModel: CharacterModel
)