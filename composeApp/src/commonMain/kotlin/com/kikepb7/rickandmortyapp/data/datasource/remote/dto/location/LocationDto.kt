package com.kikepb7.rickandmortyapp.data.datasource.remote.dto.location

import com.kikepb7.rickandmortyapp.domain.feature.locations.model.LocationModel
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val id: String,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
) {
    fun toLocationModel(): LocationModel {
        return LocationModel(
            id = id,
            name = name,
            type = type,
            dimension = dimension,
            residents = residents.map { url -> url.substringAfter(delimiter = "/") },
            url = url,
            created = created
        )
    }
}