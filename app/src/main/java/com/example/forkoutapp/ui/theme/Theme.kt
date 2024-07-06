package com.example.forkoutapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4CAF50), // Warna primer untuk tema gelap
    secondary = Color(0xFF4CAF50),
    tertiary = Color(0xFF4CAF50)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50), // Warna primer untuk tema terang
    secondary = Color(0xFF4CAF50),
    tertiary = Color(0xFF4CAF50)

    /* Warna default lainnya dapat ditimpa di sini sesuai kebutuhan */
)

@Composable
fun ForkoutappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
