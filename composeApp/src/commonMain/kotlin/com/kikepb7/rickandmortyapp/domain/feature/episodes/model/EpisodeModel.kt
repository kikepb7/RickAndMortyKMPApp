package com.kikepb7.rickandmortyapp.domain.feature.episodes.model

data class EpisodeModel(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
    val videoURL: String,
    val season: SeasonEpisode
)

enum class SeasonEpisode {
    SEASON_1,
    SEASON_2,
    SEASON_3,
    SEASON_4,
    SEASON_5,
    SEASON_6,
    SEASON_7,
    UNKNOWN
}