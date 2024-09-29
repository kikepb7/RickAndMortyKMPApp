package com.kikepb7.rickandmortyapp.domain.feature.characters

import androidx.paging.PagingData
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterOfTheDayModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getSingleCharacter(id: String): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    suspend fun getCharacterDB(): CharacterOfTheDayModel?
    suspend fun saveCharacterDB(characterOfTheDayModel: CharacterOfTheDayModel)
}