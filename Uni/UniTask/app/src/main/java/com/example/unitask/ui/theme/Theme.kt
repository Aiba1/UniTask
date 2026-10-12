package com.example.unitask.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimaryLight,
    onPrimary = OnGreenPrimaryLight,
    primaryContainer = GreenPrimaryContainerLight,
    onPrimaryContainer = OnGreenPrimaryContainerLight,
    secondary = GreenSecondaryLight,
    onSecondary = OnGreenSecondaryLight,
    secondaryContainer = GreenSecondaryContainerLight,
    onSecondaryContainer = OnGreenSecondaryContainerLight,
    tertiary = GreenTertiaryLight,
    onTertiary = OnGreenTertiaryLight,
    background = AppBackgroundLight,
    onBackground = OnAppBackgroundLight,
    surface = AppSurfaceLight,
    onSurface = OnAppSurfaceLight,
    surfaceVariant = AppSurfaceVariantLight,
    onSurfaceVariant = OnAppSurfaceVariantLight,
    outline = AppOutlineLight
)

private val DarkColorScheme = darkColorScheme(
    primary = GreenPrimaryDark,
    onPrimary = OnGreenPrimaryDark,
    primaryContainer = GreenPrimaryContainerDark,
    onPrimaryContainer = OnGreenPrimaryContainerDark,
    secondary = GreenSecondaryDark,
    onSecondary = OnGreenSecondaryDark,
    secondaryContainer = GreenSecondaryContainerDark,
    onSecondaryContainer = OnGreenSecondaryContainerDark,
    tertiary = GreenTertiaryDark,
    onTertiary = OnGreenTertiaryDark,
    background = AppBackgroundDark,
    onBackground = OnAppBackgroundDark,
    surface = AppSurfaceDark,
    onSurface = OnAppSurfaceDark,
    surfaceVariant = AppSurfaceVariantDark,
    onSurfaceVariant = OnAppSurfaceVariantDark,
    outline = AppOutlineDark
)

@Composable
fun UniTaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
