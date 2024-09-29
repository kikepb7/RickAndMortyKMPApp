package com.kikepb7.rickandmortyapp.ui.feature.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.ui.common.extensions.vertical
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.rickandmorty_placeholder

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen() {
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val state by charactersViewModel.state.collectAsState()
    val characters = state.characters.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharactersHeader()
        CharacterOfTheDay(characterModel = state.characterOfTheDay)
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )
        CharactersGridList(characters = characters)
    }

}

@Composable
fun CharactersHeader() {
    var characterSearched by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = characterSearched,
        onValueChange = { characterSearched = it },
        label = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(size = 8.dp),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Icono de búsqueda"
            )
        }
    )
}

@Composable
fun CharactersGridList(
    characters: LazyPagingItems<CharacterModel>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        /*item(span = { GridItemSpan(currentLineSpan = 2) }) {
            CharacterOfTheDay(characterModel = state.characterOfTheDay)
        }*/

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
                            color = Color.Red
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
                        CharacterItemList(characterModel = characterModel)
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
                                color = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItemList(characterModel: CharacterModel) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(percent = 24))
            .border(
                width = 2.dp,
                color = Color.Green,
                shape = RoundedCornerShape(
                    topStartPercent = 0,
                    topEndPercent = 24,
                    bottomEndPercent = 0,
                    bottomStartPercent = 24
                )
            )
            .fillMaxSize()
            .clickable { }
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = "Character image item list",
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
                .align(alignment = Alignment.BottomCenter)
                .background(
                    brush = Brush
                        .verticalGradient(
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
            Text(
                text = characterModel.gender,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .background(
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun CharacterOfTheDay(
    characterModel: CharacterModel? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(200.dp),
        shape = RoundedCornerShape(percent = 12)
    ) {
        if (characterModel == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Green)
            }
        } else {
            Box(
                contentAlignment = Alignment.BottomStart
            ) {
                AsyncImage(
                    model = characterModel.image,
                    contentDescription = "Character of the day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                0f to Color.Black.copy(alpha = 0.9f),
                                0.4f to Color.White.copy(alpha = 0f)
                            )
                        )
                )
                Text(
                    text = characterModel.name,
                    fontSize = 20.sp,
                    maxLines = 1,
                    minLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxHeight()
                        .vertical()
                        .rotate(degrees = -90f)
                )
            }
        }
    }
}