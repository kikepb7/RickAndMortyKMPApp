package com.kikepb7.rickandmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kikepb7.rickandmortyapp.data.datasource.remote.ApiService
import com.kikepb7.rickandmortyapp.data.datasource.remote.paging.CharactersPagingSource
import com.kikepb7.rickandmortyapp.domain.feature.characters.CharactersRepository
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource
): CharactersRepository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }

    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id = id).dtoToCharacterModel()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { charactersPagingSource }
        ).flow
    }
}