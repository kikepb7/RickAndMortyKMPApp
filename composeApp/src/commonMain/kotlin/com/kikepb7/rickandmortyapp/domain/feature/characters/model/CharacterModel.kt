package com.kikepb7.rickandmortyapp.domain.feature.characters.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val isAlive: Boolean,
    val species: String,
    val gender: String,
    val originName: String,
    val locationName: String,
    val image: String
)