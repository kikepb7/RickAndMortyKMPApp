package com.kikepb7.rickandmortyapp.domain.feature.characters.usecase

import com.kikepb7.rickandmortyapp.domain.feature.characters.CharactersRepository
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel

class GetRandomCharacter(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(): CharacterModel {
        val randomCharacter: Int = (1..826).random()
        return charactersRepository.getSingleCharacter(id = randomCharacter.toString())
    }
}