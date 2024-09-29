package com.kikepb7.rickandmortyapp.data.datasource.remote.dto.episode

import com.kikepb7.rickandmortyapp.data.datasource.remote.dto.InfoDto
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesWrapperDto(
    val info: InfoDto,
    val results: List<EpisodeDto>
)