package com.kikepb7.rickandmortyapp.ui.feature.characters.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kikepb7.rickandmortyapp.domain.feature.characters.CharactersRepository
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.domain.feature.episodes.model.EpisodeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CharacterDetailViewModel(
    characterModel: CharacterModel,
    private val charactersRepository: CharactersRepository
): ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState(characterModel = characterModel))
    val state = _state.asStateFlow()

    init {
        getEpisodesForCharacter(episodes = characterModel.episodes)
    }

    private fun getEpisodesForCharacter(episodes: List<String>) {
        viewModelScope.launch {
            val result = withContext(context = Dispatchers.IO) {
                charactersRepository.getEpisodesForCharacter(episodes = episodes)
            }
            _state.update { it.copy(episodes = result) }
        }
    }
}

data class CharacterDetailState(
    val characterModel: CharacterModel,
    val episodes: List<EpisodeModel>? = null
)