package com.kikepb7.rickandmortyapp.ui.core.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

fun Modifier.vertical() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints = constraints)
    layout(height = placeable.height, width = placeable.width) {
        placeable.place(
            x = - (placeable.width / 2 - placeable.height / 2),
            y = - (placeable.height / 2 - placeable.width / 2)
        )
    }
}