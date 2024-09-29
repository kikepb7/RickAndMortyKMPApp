package com.kikepb7.rickandmortyapp.data.datasource.remote.dto.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterLocationDto(
    val name: String,
    val url: String
)