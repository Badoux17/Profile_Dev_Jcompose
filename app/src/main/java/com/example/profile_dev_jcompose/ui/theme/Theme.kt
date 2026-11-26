package com.example.profile_dev_jcompose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFAAC7FF),
    onPrimary = Color(0xFF0A305F),
    secondary = Color(0xFFBDC7DC),
    onSecondary = Color(0xFF273141),
    background = Color(0xFF111318),
    onBackground = Color(0xFFE2E2E9),
    surface = Color(0xFF1D2024),
    onSurface = Color(0xFFE2E2E9)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2F5DA8),
    onPrimary = Color.White,
    secondary = Color(0xFF555F71),
    onSecondary = Color.White,
    background = Color(0xFFF8F9FF),
    onBackground = Color(0xFF191C20),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF191C20)
)

private val ProfileShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun Profile_dev_jcomposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ProfileTypography,
        shapes = ProfileShapes,
        content = content
    )
}