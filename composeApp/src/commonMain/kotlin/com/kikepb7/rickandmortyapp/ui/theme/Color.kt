package com.kikepb7.rickandmortyapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BackgroundPrimaryColor
    @Composable
//    get() = if (isSystemInDarkTheme()) primaryBlack.copy(alpha = 0.2f) else backgroundYellow.copy(alpha = 0.5f)
    get() = if (isSystemInDarkTheme()) primaryBlack.copy(alpha = 0.2f) else Green

val BackgroundSecondaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) secondaryBlack else secondaryWhite

val BackgroundTertiaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) tertiaryBlack else tertiaryWhite

val DefaultTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val PlaceholderColor
    @Composable
    get() = if (isSystemInDarkTheme()) tertiaryBlack else secondaryWhite

val BackgroundCardColor
    @Composable
    get() = if (isSystemInDarkTheme()) secondaryDarkBlue else backgroundYellow
//    get() = if (isSystemInDarkTheme()) secondaryDarkBlue else primaryLightBlue.copy(alpha = 0.5f)

val BottomBarColor
    @Composable
    get() = if (isSystemInDarkTheme()) secondaryBottomBarColor else primaryBottomBarColor.copy(alpha = 0.4f)



val Pink = Color(0xFFFF577D)
val Green = Color(0xFF5ccf92)
val Red = Color(0xFFCF5C5C)
val DarkGreen = Color(0xFF306B2A)

val primaryWhite = Color(0xFFFFFFFF)
val secondaryWhite = Color(0xFFEAE8EF)
val tertiaryWhite = Color(0xFFFAFAFA)

val primaryBlack = Color(0xFF000000)
val secondaryBlack = Color(0xFF302F2F)
val tertiaryBlack = Color(0xFF464646)

val backgroundYellow = Color(0xFFFCE46D)

val primaryLightBlue = Color(0xFFA6CCCC)
val secondaryDarkBlue = Color(0xFF043C6E)

val primaryBottomBarColor = Color(0xFFBC64E4)
val secondaryBottomBarColor = Color(0xFFBC64E4)