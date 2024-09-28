package com.kikepb7.rickandmortyapp.data.datasource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val pages: Int,
    val next: String?,
    val prev: String?
)