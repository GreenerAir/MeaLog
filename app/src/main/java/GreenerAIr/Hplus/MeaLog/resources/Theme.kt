package GreenerAIr.Hplus.MeaLog.resources

import GreenerAIr.Hplus.MeaLog.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFDD3A44),
    background = Color(0xFFEFE6DE),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFEFE6DE),
    background = Color(0xFFDD3A44),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun MeaLogTheme(
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
        typography = MaterialTheme.typography, // You can customize typography later
    ) { ThemeAwareBackground ( content = content ) }
}

@Composable
fun ThemeAwareBackground(content: @Composable () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()

    val backgroundResId = if (isDarkTheme) {
        R.drawable.drkback
    } else {
        R.drawable.lightback
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = backgroundResId),
            contentDescription = "App Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop, // or ContentScale.FillBounds
            alpha = 0.9f // Optional: adjust transparency if needed
        )

        // Your actual content
        content()
    }
}