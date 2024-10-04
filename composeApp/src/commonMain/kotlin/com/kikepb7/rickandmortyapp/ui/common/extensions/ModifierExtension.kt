package com.kikepb7.rickandmortyapp.ui.common.extensions

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.kikepb7.rickandmortyapp.ui.theme.Green
import kotlinx.datetime.Instant
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Modifier.vertical() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints = constraints)
    layout(height = placeable.height, width = placeable.width) {
        placeable.place(
            x = -(placeable.width / 2 - placeable.height / 2),
            y = -(placeable.height / 2 - placeable.width / 2)
        )
    }
}

fun Modifier.aliveBorder(isAlive: Boolean): Modifier {
    val color = if (isAlive) Green else Color.Red

    return border(4.dp, color, CircleShape)
}

fun formatDate(isoString: String): String {
    val instant = Instant.parse(isoString)

    val localDateTime = instant.toLocalDateTime(TimeZone.UTC)

    val day = localDateTime.dayOfMonth
    val month = localDateTime.month.getMonthName()
    val year = localDateTime.year

    return "$month $day, $year"
}

fun Month.getMonthName(): String {
    return when (this) {
        Month.JANUARY -> "Enero"
        Month.FEBRUARY -> "Febrero"
        Month.MARCH -> "Marzo"
        Month.APRIL -> "Abril"
        Month.MAY -> "Mayo"
        Month.JUNE -> "Junio"
        Month.JULY -> "Julio"
        Month.AUGUST -> "Agosto"
        Month.SEPTEMBER -> "Septiembre"
        Month.OCTOBER -> "Octubre"
        Month.NOVEMBER -> "Noviembre"
        Month.DECEMBER -> "Diciembre"
        else -> ""
    }
}
