package com.kikepb7.rickandmortyapp.ui.feature.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.ui.theme.BackgroundPrimaryColor
import com.kikepb7.rickandmortyapp.ui.theme.DefaultTextColor
import com.kikepb7.rickandmortyapp.ui.theme.Green
import com.kikepb7.rickandmortyapp.ui.theme.Red
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.ic_female
import rickandmortyapp.composeapp.generated.resources.ic_genderless
import rickandmortyapp.composeapp.generated.resources.ic_male
import rickandmortyapp.composeapp.generated.resources.ic_unkwnown
import rickandmortyapp.composeapp.generated.resources.rickandmorty_placeholder

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(
    navigateToDetail: (CharacterModel) -> Unit
) {
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val state by charactersViewModel.state.collectAsState()
    val characters = state.characters.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .background(color = BackgroundPrimaryColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterOfTheDay(
            characterModel = state.characterOfTheDay,
            onItemSelected = navigateToDetail
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = BackgroundPrimaryColor)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )
        CharactersGridList(characters = characters, navigateToDetail = navigateToDetail)
    }
}

@Composable
fun CharactersGridList(
    characters: LazyPagingItems<CharacterModel>,
    navigateToDetail: (CharacterModel) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundPrimaryColor)
            .padding(horizontal = 16.dp),
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        when {
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                // Initial
                item(span = { GridItemSpan(currentLineSpan = 2) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(size = 64.dp),
                            color = Green
                        )
                    }
                }
            }

            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                // Empty list
                item {
                    Text(text = "No characters to show :/")
                }
            }

            else -> {
                items(count = characters.itemCount) { position ->
                    characters[position]?.let { characterModel ->
                        CharacterItemList(
                            characterModel = characterModel,
                            onItemSelected = { character ->
                                navigateToDetail(character)
                            }
                        )
                    }
                }
                if (characters.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(currentLineSpan = 2) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .height(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(size = 64.dp),
                                color = Green
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterCard(
    characterModel: CharacterModel,
    onItemSelected: (CharacterModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val genderIcon = when (characterModel.gender) {
        "Male" -> painterResource(Res.drawable.ic_male)
        "Female" -> painterResource(Res.drawable.ic_female)
        "Genderless" -> painterResource(Res.drawable.ic_genderless)
        else -> painterResource(Res.drawable.ic_unkwnown)
    }

    val boxColor = if (characterModel.isAlive) Green else Red

    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(percent = 24))
            .border(
                width = 4.dp,
                color = boxColor,
                shape = RoundedCornerShape(
                    topStartPercent = 0,
                    topEndPercent = 24,
                    bottomEndPercent = 0,
                    bottomStartPercent = 24
                )
            )
            .clickable { onItemSelected(characterModel) }
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = "Character image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            placeholder = painterResource(resource = Res.drawable.rickandmorty_placeholder)
        )
        // NAME
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = characterModel.name,
                color = Color.White,
                fontSize = 18.sp
            )
        }
        // GENDER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                painter = genderIcon,
                contentDescription = "Gender icon",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun CharacterItemList(
    characterModel: CharacterModel,
    onItemSelected: (CharacterModel) -> Unit
) {
    CharacterCard(
        characterModel = characterModel,
        onItemSelected = onItemSelected,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun CharacterOfTheDay(
    characterModel: CharacterModel? = null,
    onItemSelected: (CharacterModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BackgroundPrimaryColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Character of the DAY",
            color = DefaultTextColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(6.dp))

        if (characterModel == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Green)
            }
        } else {
            CharacterCard(
                characterModel = characterModel,
                onItemSelected = onItemSelected,
                modifier = Modifier.size(200.dp)
            )
        }
    }
}
