package com.kikepb7.rickandmortyapp.domain.feature.characters

import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel

interface CharactersRepository {
    suspend fun getSingleCharacter(id: String): CharacterModel
}