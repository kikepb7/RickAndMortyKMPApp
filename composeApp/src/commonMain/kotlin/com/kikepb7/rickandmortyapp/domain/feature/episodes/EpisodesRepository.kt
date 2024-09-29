package com.kikepb7.rickandmortyapp.domain.feature.episodes

import androidx.paging.PagingData
import com.kikepb7.rickandmortyapp.domain.feature.episodes.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>
}