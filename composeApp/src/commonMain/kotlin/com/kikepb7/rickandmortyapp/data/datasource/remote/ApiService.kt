package com.kikepb7.rickandmortyapp.data.datasource.remote

import com.kikepb7.rickandmortyapp.data.datasource.remote.dto.CharacterDto
import com.kikepb7.rickandmortyapp.data.datasource.remote.dto.CharactersWrapperDto
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


    // LOCATIONS
}