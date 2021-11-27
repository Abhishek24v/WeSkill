package com.weskill2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Blue,
    primaryVariant = Purple700,
    secondary = Purple500,
    background = Color.Black,
    onBackground = Color.White,
    onSurface = Color.DarkGray
)

private val LightColorPalette = lightColors(
    primary = Blue,
    primaryVariant = Purple700,
    secondary = Purple500,
    background = Color.White,
    surface = Color.White
)

@Composable
fun MainTheme(isDarkMode: Boolean = isSystemInDarkTheme(),content: @Composable () -> Unit) {
    val colors = if (!isDarkMode)LightColorPalette else DarkColorPalette

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Transparent)
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun SplashTheme(content: @Composable () -> Unit) {
    val colors = DarkColorPalette

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Transparent)
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun WeSkillTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}