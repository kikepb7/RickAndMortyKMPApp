package com.kikepb7.rickandmortyapp.ui.feature.episodes

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.kikepb7.rickandmortyapp.domain.feature.episodes.model.EpisodeModel
import com.kikepb7.rickandmortyapp.domain.feature.episodes.model.SeasonEpisode
import com.kikepb7.rickandmortyapp.ui.common.components.PagingLoadingState
import com.kikepb7.rickandmortyapp.ui.common.components.PagingType
import com.kikepb7.rickandmortyapp.ui.common.components.PagingWrapper
import com.kikepb7.rickandmortyapp.ui.common.components.VideoPlayer
import com.kikepb7.rickandmortyapp.ui.theme.BackgroundPrimaryColor
import com.kikepb7.rickandmortyapp.ui.theme.DefaultTextColor
import com.kikepb7.rickandmortyapp.ui.theme.Green
import com.kikepb7.rickandmortyapp.ui.theme.PlaceholderColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.rickandmorty_fingers
import rickandmortyapp.composeapp.generated.resources.season1
import rickandmortyapp.composeapp.generated.resources.season2
import rickandmortyapp.composeapp.generated.resources.season3
import rickandmortyapp.composeapp.generated.resources.season4
import rickandmortyapp.composeapp.generated.resources.season5
import rickandmortyapp.composeapp.generated.resources.season6
import rickandmortyapp.composeapp.generated.resources.season7

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {
    val episodesViewModel = koinViewModel<EpisodesViewModel>()
    val state by episodesViewModel.state.collectAsState()
    val episodes = state.episodes.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundPrimaryColor)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initialView = { PagingLoadingState() },
            itemView = {
                EpisodeItemList(episode = it) { url ->
                    episodesViewModel.onPlaySelected(url = url)
                }
            }
        )
        EpisodePlayer(
            playVideo = state.playVideo,
            onCloseVideo = { episodesViewModel.onCloseVideo() })
    }
}

@Composable
fun EpisodeItemList(
    episode: EpisodeModel,
    onEpisodeSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(horizontal = 16.dp)
            .clickable { onEpisodeSelected(episode.videoURL) }
    ) {
        Image(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth(),
            contentDescription = "Episode image",
            contentScale = ContentScale.Crop,
            painter = painterResource(resource = getSeasonImage(seasonEpisode = episode.season))
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = episode.episode,
            color = DefaultTextColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EpisodePlayer(
    playVideo: String,
    onCloseVideo: () -> Unit
) {
    AnimatedContent(playVideo.isNotBlank()) { condition ->
        if (condition) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(16.dp),
                shape = CardDefaults.elevatedShape
            ) {
                Box(
                    modifier = Modifier.background(Color.Black)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        VideoPlayer(
                            modifier = Modifier.fillMaxSize(),
                            url = playVideo
                        )
                    }
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(8.dp)
                                .clickable { onCloseVideo() },
                            contentDescription = "Close icon",
                            imageVector = Icons.Default.Close,
                            tint = Green
                        )
                    }
                }
            }
        } else {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.elevatedCardColors()
                    .copy(containerColor = PlaceholderColor),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.rickandmorty_fingers),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(200.dp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Aw jeez, you gotta click the episode!!",
                        color = DefaultTextColor,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

fun getSeasonImage(seasonEpisode: SeasonEpisode): DrawableResource {
    return when (seasonEpisode) {
        SeasonEpisode.SEASON_1 -> Res.drawable.season1
        SeasonEpisode.SEASON_2 -> Res.drawable.season2
        SeasonEpisode.SEASON_3 -> Res.drawable.season3
        SeasonEpisode.SEASON_4 -> Res.drawable.season4
        SeasonEpisode.SEASON_5 -> Res.drawable.season5
        SeasonEpisode.SEASON_6 -> Res.drawable.season6
        SeasonEpisode.SEASON_7 -> Res.drawable.season7
        SeasonEpisode.UNKNOWN -> Res.drawable.season1
    }
}