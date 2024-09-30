package com.kikepb7.rickandmortyapp.ui.feature.characters.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.domain.feature.episodes.model.EpisodeModel
import com.kikepb7.rickandmortyapp.ui.common.components.TextTitle
import com.kikepb7.rickandmortyapp.ui.common.extensions.aliveBorder
import com.kikepb7.rickandmortyapp.ui.theme.BackgroundPrimaryColor
import com.kikepb7.rickandmortyapp.ui.theme.BackgroundSecondaryColor
import com.kikepb7.rickandmortyapp.ui.theme.BackgroundTertiaryColor
import com.kikepb7.rickandmortyapp.ui.theme.DefaultTextColor
import com.kikepb7.rickandmortyapp.ui.theme.Green
import com.kikepb7.rickandmortyapp.ui.theme.Pink
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.rickandmorty_background2

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailScreen(
    characterModel: CharacterModel
) {
    val characterDetailViewModel =
        koinViewModel<CharacterDetailViewModel>(parameters = { parametersOf(characterModel) })
    val state by characterDetailViewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundPrimaryColor)
            .verticalScroll(state = scrollState)
    ) {
        MainCharacterDetailHeader(characterModel = characterModel)
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(color = BackgroundSecondaryColor)
        ) {
            CharacterInfo(characterModel = characterModel)
            CharacterEpisodesList(episodes = state.episodes)
        }
    }
}

@Composable
fun MainCharacterDetailHeader(characterModel: CharacterModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(resource = Res.drawable.rickandmorty_background2),
            contentScale = ContentScale.Crop,
            contentDescription = "Background header"
        )
        CharacterDetailHeader(characterModel = characterModel)
    }
}

@Composable
fun CharacterDetailHeader(characterModel: CharacterModel) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = characterModel.name,
                color = Pink,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Especie: ${characterModel.species}",
                color = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Box(
                    modifier = Modifier
                        .size(205.dp)
                        .clip(shape = CircleShape)
                        .background(Color.Black.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = characterModel.image,
                        contentDescription = "${characterModel.name} image",
                        modifier = Modifier
                            .size(190.dp)
                            .clip(shape = CircleShape)
                            .aliveBorder(isAlive = characterModel.isAlive),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = if (characterModel.isAlive) "ALIVE" else "DEAD",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(percent = 30))
                        .background(color = if (characterModel.isAlive) Green else Color.Red)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CharacterInfo(characterModel: CharacterModel) {
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors().copy(containerColor = BackgroundTertiaryColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            TextTitle(text = "ABOUT THE CHARACTER")
            Spacer(modifier = Modifier.height(8.dp))
            InfoDetail(title = "Origin: ", detail = characterModel.originName)
            Spacer(modifier = Modifier.height(2.dp))
            InfoDetail(title = "Gender: ", detail = characterModel.gender)
        }
    }
}

@Composable
fun InfoDetail(title: String, detail: String) {
    Row {
        Text(
            text = title,
            color = DefaultTextColor,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = detail,
            color = Green
        )
    }
}

@Composable
fun CharacterEpisodesList(episodes: List<EpisodeModel>?) {
    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor)
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (episodes == null) {
                CircularProgressIndicator(color = Green)
            } else {
                Column {
                    TextTitle(text = "EPISODES")
                    Spacer(modifier = Modifier.height(8.dp))
                    episodes.forEach { episode ->
                        EpisodeItem(episodeModel = episode)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(episodeModel: EpisodeModel) {
    Text(
        text = episodeModel.name,
        color = Green,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = episodeModel.episode,
        color = DefaultTextColor
    )
}
