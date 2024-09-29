package com.kikepb7.rickandmortyapp.ui.feature.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kikepb7.rickandmortyapp.domain.feature.episodes.EpisodesRepository
import com.kikepb7.rickandmortyapp.domain.feature.episodes.model.EpisodeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update

class EpisodesViewModel(
    private val episodesRepository: EpisodesRepository
): ViewModel() {

    private val _state = MutableStateFlow<EpisodesState>(EpisodesState())
    val state = _state.asStateFlow()

    init {
        _state.update { state ->
            state.copy(episodes = episodesRepository.getAllEpisodes().cachedIn(scope = viewModelScope))
        }
    }

    fun onPlaySelected(url: String) {
        _state.update { state ->
            state.copy(playVideo = url)
        }
    }

    fun onCloseVideo() {
        _state.update { state ->
            state.copy(playVideo = "")
        }
    }
}

data class EpisodesState(
    val episodes: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    val playVideo: String = ""
)