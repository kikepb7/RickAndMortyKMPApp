package com.kikepb7.rickandmortyapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kikepb7.rickandmortyapp.domain.feature.characters.model.CharacterModel
import com.kikepb7.rickandmortyapp.ui.feature.characters.CharacterOfTheDay

@Preview
@Composable
fun Preview() {
    CharacterOfTheDay(
        characterModel = CharacterModel(
            id = 3,
            image = "",
            isAlive = true,
            name = "Prueba"
        )
    )
}