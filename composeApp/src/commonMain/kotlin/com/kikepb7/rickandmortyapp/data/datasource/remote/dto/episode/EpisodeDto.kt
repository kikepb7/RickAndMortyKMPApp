package com.kikepb7.rickandmortyapp.data.datasource.remote.dto.episode

import com.kikepb7.rickandmortyapp.domain.feature.episodes.model.EpisodeModel
import com.kikepb7.rickandmortyapp.domain.feature.episodes.model.SeasonEpisode
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) {
    fun toEpisodeModel(): EpisodeModel {
        val season = getSeasonFromEpisode(episode = episode)
        val video = getVideoUrlFromSeason(season = season)
        return EpisodeModel(
            id = id,
            name = name,
            air_date = air_date,
            episode = episode,
            characters = characters.map { url -> url.substringAfter(delimiter = "/") },
            url = url,
            created = created,
            videoURL = video,
            season = season
        )
    }

    private fun getSeasonFromEpisode(episode: String): SeasonEpisode {
        return when {
            episode.startsWith(prefix = "S01") -> SeasonEpisode.SEASON_1
            episode.startsWith(prefix = "S02") -> SeasonEpisode.SEASON_2
            episode.startsWith(prefix = "S03") -> SeasonEpisode.SEASON_3
            episode.startsWith(prefix = "S04") -> SeasonEpisode.SEASON_4
            episode.startsWith(prefix = "S05") -> SeasonEpisode.SEASON_5
            episode.startsWith(prefix = "S06") -> SeasonEpisode.SEASON_6
            episode.startsWith(prefix = "S07") -> SeasonEpisode.SEASON_7
            else -> SeasonEpisode.UNKNOWN
        }
    }

    // TODO --> Complete with the correct video links!!
    private fun getVideoUrlFromSeason(season: SeasonEpisode): String {
        return when (season) {
            SeasonEpisode.SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-6a8e6.appspot.com/o/rickandmorty-trailer.mp4?alt=media&token=887f30b9-f232-413e-a8cd-4ff20a41ff14"
            SeasonEpisode.SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-6a8e6.appspot.com/o/rickandmorty-trailer.mp4?alt=media&token=887f30b9-f232-413e-a8cd-4ff20a41ff14"
            SeasonEpisode.SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-6a8e6.appspot.com/o/rickandmorty-trailer.mp4?alt=media&token=887f30b9-f232-413e-a8cd-4ff20a41ff14"
            SeasonEpisode.SEASON_4 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-6a8e6.appspot.com/o/rickandmorty-trailer.mp4?alt=media&token=887f30b9-f232-413e-a8cd-4ff20a41ff14"
            SeasonEpisode.SEASON_5 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-6a8e6.appspot.com/o/rickandmorty-trailer.mp4?alt=media&token=887f30b9-f232-413e-a8cd-4ff20a41ff14"
            SeasonEpisode.SEASON_6 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-6a8e6.appspot.com/o/rickandmorty-trailer.mp4?alt=media&token=887f30b9-f232-413e-a8cd-4ff20a41ff14"
            SeasonEpisode.SEASON_7 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-6a8e6.appspot.com/o/rickandmorty-trailer.mp4?alt=media&token=887f30b9-f232-413e-a8cd-4ff20a41ff14"
            SeasonEpisode.UNKNOWN -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-6a8e6.appspot.com/o/rickandmorty-trailer.mp4?alt=media&token=887f30b9-f232-413e-a8cd-4ff20a41ff14"
        }
    }
}