package com.kikepb7.rickandmortyapp.domain.feature.locations.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationModel(
    val id: String,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)