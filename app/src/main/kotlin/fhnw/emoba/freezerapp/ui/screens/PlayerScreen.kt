package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    with(model) {
        Column(
            content = {
                Column(content = {
                    if (currentlyPlayingCover != null) {
                        currentlyPlayingCover?.let {
                            Image(
                                bitmap = it,
                                contentDescription = "Album Cover",
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .fillMaxWidth()
                                    .clip(CircleShape)
                                    .border(2.dp, MaterialTheme.colors.primary, CircleShape)
                            )
                        } ?: run {
                            EmptyCircularCoverBigPlayer()
                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    SingleLineTextBold(text = currentlyPlaying.title, 24.sp)
                    SingleLineTextLight(text = currentlyPlaying.artist.name)
                }, horizontalAlignment = Alignment.CenterHorizontally)
                Row(
                    content = {
                        IconButton(
                            onClick = { /*TODO: to start/last track*/ },
                            modifier = Modifier.size(100.dp)
                        ) {
                            Icon(
                                Icons.Outlined.SkipPrevious,
                                "Zum Anfang",
                                tint = MaterialTheme.colors.primary,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                        //TODO: if playing, stop icon
                        IconButton(
                            onClick = { /*TODO: player.play()*/ },
                            modifier = Modifier.size(100.dp)
                        ) {
                            Icon(
                                Icons.Outlined.PlayArrow,
                                "Abspielen",
                                tint = MaterialTheme.colors.primary,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        IconButton(
                            onClick = { /*TODO: next track*/ },
                            modifier = Modifier.size(100.dp)
                        ) {
                            Icon(
                                Icons.Outlined.SkipNext,
                                "Nächstes Lied",
                                tint = MaterialTheme.colors.primary,
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
                .fillMaxSize(),
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
                IconButton(onClick = {
                    /*TODO: add current track to favoriteTracks*/
                    //Vorsicht: nicht nur .contains, vergleich mit id und merkliste track von foundTracks hinzufügen
                    if (!favoriteTracks.map { t -> t.id }.contains(currentlyPlaying.id)){
                        val ex = tracksFound.find { track -> track.id == currentlyPlaying.id}
                        if (ex != null) {
                            favoriteTracks = favoriteTracks + ex
                        }
                    }
                }) {
                    Icon(Icons.Filled.PlaylistAdd, "Merken")
                }
                //TODO: add miniplayer of currentlyPlaying
            },
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.secondary,
            elevation = 0.dp,
        )
    }
}
