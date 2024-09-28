package com.kikepb7.rickandmortyapp.data.repository

import com.kikepb7.rickandmortyapp.data.datasource.remote.ApiService
import com.kikepb7.rickandmortyapp.domain.feature.characters.CharactersRepository
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel

class CharactersRepositoryImpl(
    private val api: ApiService
): CharactersRepository {
    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id = id).dtoToCharacterModel()
    }
}