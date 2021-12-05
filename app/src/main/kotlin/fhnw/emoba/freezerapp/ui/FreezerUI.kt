package fhnw.emoba.freezerapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen
import fhnw.emoba.freezerapp.ui.screens.*
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
                    Screen.FAVORITETRACKS -> {
                        FavoriteTracksScreen(model = model)
                    }
                    Screen.LASTPLAYED -> {
                        LastPlayedScreen(model = model)
                    }
                    Screen.PLAYER -> {
                        PlayerScreen(model = model)
                    }
                    Screen.SEARCH -> {
                        SearchScreen(model = model)
                    }
                    Screen.PLAYLIST -> {
                        PlaylistScreen(model = model)
                    }
                    Screen.ARTISTDETAIL -> {
                        ArtistDetailScreen(model = model)
                    }
                    Screen.ALBUMDETAIL -> {
                        AlbumDetailScreen(model = model)
                    }
                    Screen.RADIODETAIL -> {
                        RadioDetailScreen(model = model)
                    }
                    Screen.IMPRESSUM -> {
                        ImpressumScreen(model = model)
                    }
                }
            }
        }
    }
}
