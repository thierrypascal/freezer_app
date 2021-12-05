package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.material.icons.outlined.Stop
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen

@Composable
fun PlayerScreen(model: FreezerModel) {
    val scaffoldState = rememberScaffoldState()

    with(model) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar(model = model) },
            bottomBar = { BottomBar(model = model) },
            content = { Body(model = model) },
        )
    }
}

@Composable
private fun TopBar(model: FreezerModel) {
    StandardTopAppBar(model)
}

@Composable
private fun Body(model: FreezerModel) {
    val scrollState = rememberScrollState()

    with(model) {
        Column(
            content = {
                Column(content = {
                    if (isLoadingImg) {
                        CircularProgressIndicator()
                    } else {
                        if (currentlyPlayingCover != null) {
                            if (currentlyPlayingCover != null) {
                                Image(
                                    bitmap = currentlyPlayingCover!!,
                                    contentDescription = "Album Cover",
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .fillMaxWidth()
                                        .clip(CircleShape)
                                        .border(2.dp, MaterialTheme.colors.primary, CircleShape)
                                )
                            } else {
                                EmptyCircularCoverBigPlayer()
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    SingleLineTextBold(text = currentlyPlaying.title, 24.sp)
                    SingleLineTextLight(text = currentlyPlaying.artist.name)
                }, horizontalAlignment = Alignment.CenterHorizontally)
                Row(
                    content = {
                        IconButton(
                            onClick = { fromStart() },
                            modifier = Modifier.size(100.dp)
                        ) {
                            Icon(
                                Icons.Outlined.SkipPrevious,
                                "Zum Anfang",
                                tint = MaterialTheme.colors.primary,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                        if (playerIsReady) {
                            IconButton(
                                onClick = { startPlayer() },
                                modifier = Modifier.size(100.dp)
                            ) {
                                Icon(
                                    Icons.Outlined.PlayArrow,
                                    "Abspielen",
                                    tint = MaterialTheme.colors.primary,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { pausePlayer() },
                                modifier = Modifier.size(100.dp)
                            ) {
                                Icon(
                                    Icons.Outlined.Stop,
                                    "Abspielen",
                                    tint = MaterialTheme.colors.primary,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                        IconButton(
                            onClick = { nextTrack() },
                            modifier = Modifier.size(100.dp)
                        ) {
                            Icon(
                                Icons.Outlined.SkipNext,
                                "Nächstes Lied",
                                tint = if (playlist.isNotEmpty()) MaterialTheme.colors.primary else Color.DarkGray,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.Center
                )
            },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 60.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        )
    }
}

@Composable
private fun BottomBar(model: FreezerModel) {
    with(model) {
        BottomAppBar(
            content = {
                IconButton(onClick = { model.currentScreen = Screen.PLAYLIST }) {
                    Icon(Icons.Filled.QueueMusic, "Playlist")
                }
                IconButton(onClick = {
                    if (!favoriteTracks.map { t -> t.id }.contains(currentlyPlaying.id)) {
                        val ex = tracksFound.find { track -> track.id == currentlyPlaying.id }
                        if (ex != null) {
                            favoriteTracks = favoriteTracks + ex
                        } else {
                            favoriteTracks = favoriteTracks + currentlyPlaying
                        }
                    }
                }) {
                    Icon(Icons.Filled.PlaylistAdd, "Merken")
                }
            },
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.secondary,
            elevation = 0.dp,
        )
    }
}
