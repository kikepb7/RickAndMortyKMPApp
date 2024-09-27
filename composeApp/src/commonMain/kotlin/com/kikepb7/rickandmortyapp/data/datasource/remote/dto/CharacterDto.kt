package com.kikepb7.rickandmortyapp.data.datasource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: String,
    val status: String,
    val image: String,
)