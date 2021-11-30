package fhnw.emoba.freezerapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val AppDarkColors = darkColors(
    primary             = Color(0xFFDFBE6D),
    primaryVariant      = Color(0xFFDFBE6D),
    secondary           = Color(0xFF7D7171),
    secondaryVariant    = Color(0xFF7D7171),
    background          = Color(0xFF050505),
    surface             = Color(0xFF050505),
    error               = Color(0xFFF03D3D),

    onPrimary           = Color.Black,
    onSecondary         = Color.Black,
    onBackground        = Color.White,
    onSurface           = Color.White,
    onError             = Color.White,
)

@Composable
fun FreezerAppTheme(content: @Composable() () -> Unit){
    val colors = AppDarkColors

    MaterialTheme(
        colors = colors,
        content = content,
    )
}
