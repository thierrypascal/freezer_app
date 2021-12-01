package fhnw.emoba.freezerapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen
import fhnw.emoba.freezerapp.ui.screens.HomeScreen
import fhnw.emoba.freezerapp.ui.screens.SearchScreen
import fhnw.emoba.freezerapp.ui.theme.FreezerAppTheme


@Composable
fun FreezerUI(model: FreezerModel) {
    with(model) {
        FreezerAppTheme {
            Crossfade(targetState = currentScreen) { screen ->
                when (screen) {
                    Screen.HOME -> {
                        HomeScreen(model = model)
                    }
                    Screen.FAVORITETRACKS -> TODO()
                    Screen.LASTPLAYED -> TODO()
                    Screen.PLAYER -> TODO()
                    Screen.SEARCH -> {
                        SearchScreen(model = model)
                    }
                }
            }
        }
    }
}
