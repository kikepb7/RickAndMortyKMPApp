package com.kikepb7.rickandmortyapp.domain.feature.characters.model

data class CharacterModel(
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String
)