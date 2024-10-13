package com.kikepb7.rickandmortyapp.domain.feature.characters.usecase

import com.kikepb7.rickandmortyapp.domain.feature.characters.CharactersRepository
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterOfTheDayModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacterUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(): CharacterModel {

        val characterOfTheDay = charactersRepository.getCharacterDB()
        val selectedDay = getCurrentDay()

        return if (characterOfTheDay != null && characterOfTheDay.selectedDay == getCurrentDay()) {
            characterOfTheDay.characterModel
        } else {
            val result = generateRandomCharacter()
            charactersRepository.saveCharacterDB(CharacterOfTheDayModel(characterModel = result, selectedDay = selectedDay))
            result
        }
    }

    private suspend fun generateRandomCharacter(): CharacterModel {
        val randomCharacter: Int = (1..826).random()
        return charactersRepository.getSingleCharacter(id = randomCharacter.toString())
    }

    private fun getCurrentDay(): String {
        val instant = Clock.System.now()
        val localTime = instant.toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }
}