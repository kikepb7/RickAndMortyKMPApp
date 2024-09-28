package com.kikepb7.rickandmortyapp.data.datasource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharactersWrapperDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)