package com.kikepb7.rickandmortyapp.ui.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kikepb7.rickandmortyapp.ui.theme.DefaultTextColor

@Composable
fun TextTitle(text: String) {
    Text(
        text = text.uppercase(),
        color = DefaultTextColor,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    )
}