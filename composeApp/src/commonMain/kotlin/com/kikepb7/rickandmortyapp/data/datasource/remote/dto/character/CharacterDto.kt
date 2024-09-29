package com.kikepb7.rickandmortyapp.data.datasource.remote.dto.character

import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginDto,
    val location: CharacterLocationDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) {
    fun dtoToCharacterModel(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            isAlive = status.lowercase() == "alive",
            species = species,
            gender = gender,
            originName = origin.name,
            locationName = location.name,
            image = image
        )
    }
}