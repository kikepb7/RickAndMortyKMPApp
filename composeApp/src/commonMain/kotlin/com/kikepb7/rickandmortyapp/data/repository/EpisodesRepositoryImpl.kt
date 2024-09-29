package com.kikepb7.rickandmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kikepb7.rickandmortyapp.data.datasource.remote.ApiService
import com.kikepb7.rickandmortyapp.data.datasource.remote.paging.EpisodesPagingSource
import com.kikepb7.rickandmortyapp.data.repository.CharactersRepositoryImpl.Companion.MAX_ITEMS
import com.kikepb7.rickandmortyapp.data.repository.CharactersRepositoryImpl.Companion.PREFETCH_ITEMS
import com.kikepb7.rickandmortyapp.domain.feature.episodes.EpisodesRepository
import com.kikepb7.rickandmortyapp.domain.feature.episodes.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

class EpisodesRepositoryImpl(
    private val api: ApiService,
    private val episodesPagingSource: EpisodesPagingSource
) : EpisodesRepository {

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> {
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { episodesPagingSource }).flow
    }
}