package com.kikepb7.rickandmortyapp.ui.feature.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.ui.common.components.PagingLoadingState
import com.kikepb7.rickandmortyapp.ui.common.components.PagingType
import com.kikepb7.rickandmortyapp.ui.common.components.PagingWrapper
import com.kikepb7.rickandmortyapp.ui.theme.BackgroundCardColor
import com.kikepb7.rickandmortyapp.ui.theme.BackgroundPrimaryColor
import com.kikepb7.rickandmortyapp.ui.theme.DarkGreen
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
        PagingWrapper(
            pagingType = PagingType.COLUMN,
            pagingItems = characters,
            initialView = { PagingLoadingState() },
            itemView = {
                CharacterItemList(characterModel = it, onItemSelected = navigateToDetail)
            }
        )
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

    val boxColor = if (characterModel.isAlive) DarkGreen else Red

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemSelected(characterModel) }
            .clip(shape = RoundedCornerShape(percent = 24)),
        colors = CardDefaults.cardColors(containerColor = BackgroundCardColor)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .border(
                        width = 4.dp,
                        color = boxColor
                    )
            ) {
                AsyncImage(
                    model = characterModel.image,
                    contentDescription = "Character image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp),
                    placeholder = painterResource(resource = Res.drawable.rickandmorty_placeholder)
                )
            }
            // NAME
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Row {
                    Text(
                        text = characterModel.name,
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    Icon(
                        painter = genderIcon,
                        contentDescription = "Gender icon",
                        tint = Green,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = characterModel.originName,
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    text = characterModel.species,
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
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
