package com.kikepb7.rickandmortyapp.data.repository

import com.kikepb7.rickandmortyapp.data.datasource.remote.ApiService
import com.kikepb7.rickandmortyapp.domain.feature.episodes.EpisodesRepository

class EpisodesRepositoryImpl(
    private val api: ApiService
): EpisodesRepository {

}