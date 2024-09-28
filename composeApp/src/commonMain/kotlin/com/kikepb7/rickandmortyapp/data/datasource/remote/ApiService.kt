package com.kikepb7.rickandmortyapp.data.datasource.remote

import com.kikepb7.rickandmortyapp.data.datasource.remote.dto.CharacterDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(
    private val httpClient: HttpClient
) {
    suspend fun getSingleCharacter(id: String): CharacterDto {
        return httpClient.get("/api/character/$id").body()
    }
}