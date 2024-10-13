package com.kikepb7.rickandmortyapp.ui.feature.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kikepb7.rickandmortyapp.domain.feature.characters.CharactersRepository
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.domain.feature.characters.usecase.GetRandomCharacterUseCase
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
    val getRandomCharacterUseCase: GetRandomCharacterUseCase,
    private val charactersRepository: CharactersRepository
): ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getRandomCharacterUseCase()
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
                characters = charactersRepository.getAllCharacters().cachedIn(scope = viewModelScope)
            )
        }
    }
}

data class CharactersState(
    val characterOfTheDay: CharacterModel? = null,
    val characters: Flow<PagingData<CharacterModel>> = emptyFlow()
)