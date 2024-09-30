package com.kikepb7.rickandmortyapp.ui.common.extensions

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.kikepb7.rickandmortyapp.ui.theme.Green

fun Modifier.vertical() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints = constraints)
    layout(height = placeable.height, width = placeable.width) {
        placeable.place(
            x = - (placeable.width / 2 - placeable.height / 2),
            y = - (placeable.height / 2 - placeable.width / 2)
        )
    }
}

fun Modifier.aliveBorder(isAlive: Boolean): Modifier {
    val color = if (isAlive) Green else Color.Red

    return border(4.dp, color, CircleShape)
}