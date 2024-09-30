package com.kikepb7.rickandmortyapp.domain.feature.characters.model

import com.kikepb7.rickandmortyapp.data.datasource.database.entity.CharacterEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class CharacterOfTheDayModel(
    val characterModel: CharacterModel,
    val selectedDay: String
) {
    fun toEntity(): CharacterEntity {
        return CharacterEntity(
            id = characterModel.id,
            name = characterModel.name,
            isAlive = characterModel.isAlive,
            species = characterModel.species,
            gender = characterModel.gender,
            originName = characterModel.originName,
            locationName = characterModel.locationName,
            episodes = Json.encodeToString(value = characterModel.episodes),
            image = characterModel.image,
            selectedDay = selectedDay
        )
    }
}