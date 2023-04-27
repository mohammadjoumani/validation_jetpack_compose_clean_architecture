package com.mmj.validation.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

enum class AppTheme {
    Light, Dark, Default
}


private val DarkColorScheme = darkColorScheme(
    primary = colorBlue,
    secondary = colorBlue1,
    background = colorBlack,
    error = colorRed,
    surface = colorBlack1,
    onPrimary = colorWhite,
    onSecondary = colorWhite,
    onBackground = colorWhite,
    onError = colorWhite,
    onSurface = colorWhite1
)

private val LightColorScheme = lightColorScheme(
    primary = colorBlue,
    secondary = colorBlue1,
    background = colorWhite,
    error = colorRed,
    surface = colorWhite1,
    onPrimary = colorWhite,
    onSecondary = colorWhite,
    onBackground = colorBlack,
    onError = colorWhite,
    onSurface = colorBlack1
)

@Composable
fun ValidationTheme(
    appTheme: AppTheme = AppTheme.Light,
    isDarkMode: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val colorScheme = when (appTheme) {
        AppTheme.Default -> {
            if (isDarkMode) {
                DarkColorScheme
            } else {
                LightColorScheme
            }
        }
        AppTheme.Light -> {
            LightColorScheme
        }
        AppTheme.Dark -> {
            DarkColorScheme
        }
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorScheme.primary,
            darkIcons = false,
        )
    }
    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}