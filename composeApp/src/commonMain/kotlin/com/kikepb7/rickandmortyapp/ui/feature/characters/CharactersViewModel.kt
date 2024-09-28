package com.kikepb7.rickandmortyapp.ui.feature.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.kikepb7.rickandmortyapp.domain.feature.characters.CharactersRepository
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.domain.feature.characters.usecase.GetRandomCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(
    val getRandomCharacter: GetRandomCharacter,
    private val charactersRepository: CharactersRepository
): ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getRandomCharacter()
            }

            _state.update { state ->
                state.copy(characterOfTheDay = result)
            }
        }

        getAllCharacters()
    }

    private fun getAllCharacters() {
        _state.update { state ->
            state.copy(
                characters = charactersRepository.getAllCharacters()
            )
        }
    }
}

data class CharactersState(
    val characterOfTheDay: CharacterModel? = null,
    val characters: Flow<PagingData<CharacterModel>> = emptyFlow()
)