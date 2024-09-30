package com.kikepb7.rickandmortyapp.data.datasource.remote

import com.kikepb7.rickandmortyapp.data.datasource.remote.dto.character.CharacterDto
import com.kikepb7.rickandmortyapp.data.datasource.remote.dto.character.CharactersWrapperDto
import com.kikepb7.rickandmortyapp.data.datasource.remote.dto.episode.EpisodeDto
import com.kikepb7.rickandmortyapp.data.datasource.remote.dto.episode.EpisodesWrapperDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(
    private val httpClient: HttpClient
) {
    // CHARACTERS
    suspend fun getSingleCharacter(id: String): CharacterDto {
        return httpClient.get("/api/character/$id").body()
    }

    suspend fun getAllCharacters(page: Int): CharactersWrapperDto {
        return httpClient.get("/api/character/") {
            parameter(key = "page", value = page)
        }.body()
    }

    // EPISODES
    suspend fun getAllEpisodes(page: Int): EpisodesWrapperDto {
        return httpClient.get("/api/episode/") {
            parameter(key = "page", value = page)
        }.body()
    }

    suspend fun getEpisodes(episodes: String): List<EpisodeDto> {
        return httpClient.get("/api/episode/$episodes").body()
    }

    suspend fun getSingleEpisode(episodeId: String): EpisodeDto {
        return httpClient.get("/api/episode/$episodeId").body()
    }

    // LOCATIONS
}