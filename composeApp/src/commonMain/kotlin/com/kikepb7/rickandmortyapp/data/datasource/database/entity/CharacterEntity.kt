package com.kikepb7.rickandmortyapp.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterOfTheDayModel

@Entity(tableName = "characteroftheday")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val isAlive: Boolean,
    val species: String,
    val gender: String,
    val originName: String,
    val locationName: String,
    val image: String,
    val selectedDay: String
) {
    fun toCharacterOfTheDayModel(): CharacterOfTheDayModel {
        return CharacterOfTheDayModel(
            characterModel = CharacterModel(
                id = id,
                name = name,
                isAlive = isAlive,
                species = species,
                gender = gender,
                originName = originName,
                locationName = locationName,
                image = image),
            selectedDay = selectedDay
        )
    }
}