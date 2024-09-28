package com.kikepb7.rickandmortyapp.data.repository

import com.kikepb7.rickandmortyapp.data.datasource.remote.ApiService
import com.kikepb7.rickandmortyapp.domain.feature.locations.LocationsRepository

class LocationsRepositoryImpl(
    private val api: ApiService
): LocationsRepository {

}