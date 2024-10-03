package com.kikepb7.rickandmortyapp.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kikepb7.rickandmortyapp.ui.theme.DefaultTextColor

@Composable
fun TextTitle(text: String) {
    Box(Modifier.width(IntrinsicSize.Max)) {
        Box(
            Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(if (text.length < 3) 1f else 0.7f)
                .height(7.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Red,
                            Color.Red.copy(alpha = 0f)
                        )
                    ),
                    shape = CircleShape
                )
        )
        Text(
            modifier = Modifier.padding(bottom = 1.dp),
            text = text.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = DefaultTextColor,
            style = MaterialTheme.typography.titleMedium
        )
    }
}