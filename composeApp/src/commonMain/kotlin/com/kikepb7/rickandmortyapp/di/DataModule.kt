package com.kikepb7.rickandmortyapp.di

import com.kikepb7.rickandmortyapp.data.datasource.remote.ApiService
import com.kikepb7.rickandmortyapp.data.datasource.remote.paging.CharactersPagingSource
import com.kikepb7.rickandmortyapp.data.repository.CharactersRepositoryImpl
import com.kikepb7.rickandmortyapp.data.repository.EpisodesRepositoryImpl
import com.kikepb7.rickandmortyapp.data.repository.LocationsRepositoryImpl
import com.kikepb7.rickandmortyapp.domain.feature.characters.CharactersRepository
import com.kikepb7.rickandmortyapp.domain.feature.episodes.EpisodesRepository
import com.kikepb7.rickandmortyapp.domain.feature.locations.LocationsRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(plugin = ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
            install(plugin = DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com"
                }
            }
        }
    }

    factoryOf(::ApiService)
    factory <CharactersRepository> { CharactersRepositoryImpl(api = get(), charactersPagingSource = get(), rickMortyDatabase = get()) }
    factory <EpisodesRepository> { EpisodesRepositoryImpl(api = get()) }
    factory <LocationsRepository> { LocationsRepositoryImpl(api = get()) }
    factoryOf(::CharactersPagingSource)
}