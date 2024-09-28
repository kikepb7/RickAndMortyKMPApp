package com.kikepb7.rickandmortyapp.data.datasource.remote.dto

import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val status: String,
    val image: String,
) {
    fun dtoToCharacterModel(): CharacterModel {
        return CharacterModel(
            id = id,
            isAlive = status.lowercase() == "alive",
            image = image
        )
    }
}