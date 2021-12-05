package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.R
import fhnw.emoba.freezerapp.data.classes.Track
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen

@Composable
fun HomeScreen(model: FreezerModel) {
    val scaffoldState = rememberScaffoldState()

    with(model) {
        getCoverAsync()
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
    with(model) {
        TopAppBar(
            title = { InofficialLogo(model) },
            actions = { SearchIcon(model) },
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.secondary,
            elevation = 0.dp,
        )
    }
}

@Composable
private fun Body(model: FreezerModel) {
    with(model) {
        Column(
            content = {
                Column(content = {
                    BasicRow(
                        content = {
                            SingleLineTextBold("Zuletzt gespielt", 18.sp)
                            IconButton(onClick = { currentScreen = Screen.LASTPLAYED }) {
                                Icon(Icons.Filled.ArrowForwardIos, "Zuletzt gespielt")
                            }
                        },
                    )
                    when {
                        lastPlayed.isEmpty() -> Text("Noch keine Tracks gespielt")
                        else -> HorizontalTrackList(model, lastPlayed, 0)
                    }
                    BasicRow(
                        content = {
                            SingleLineTextBold("Merkliste", 18.sp)
                            IconButton(onClick = { currentScreen = Screen.FAVORITETRACKS }) {
                                Icon(Icons.Filled.ArrowForwardIos, "Merkliste")
                            }
                        },
                    )
                    when {
                        favoriteTracks.isEmpty() -> Text("Noch keine gemerkten Tracks")
                        else -> HorizontalTrackList(model, favoriteTracks, 1)
                    }
                    BasicRow(
                        content = {
                            SingleLineTextBold("Listensuche", 18.sp)
                            IconButton(onClick = { currentScreen = Screen.SEARCH }) {
                                Icon(Icons.Filled.ArrowForwardIos, "Listensuche")
                            }
                        },
                    )
                })
                //TODO: tap on Logo to get "Impressum"/README as Scrollable Text
                Column(content = {
                    Image(
                        painter = painterResource(R.drawable.deezerlogo_white),
                        contentDescription = "Deezer Logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                    )
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                        color = MaterialTheme.colors.primary,
                        thickness = 6.dp
                    )
                    Spacer(Modifier.height(100.dp))
                }, modifier = Modifier.clickable(onClick = {
                    currentScreen = Screen.IMPRESSUM
                }))
            },
            modifier = Modifier
                .padding(8.0.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        )
    }
}

@Composable
private fun BottomBar(model: FreezerModel) {
    with(model) {
        StandardAppBottomBar(model)
    }
}

@Composable
private fun HorizontalTrackList(model: FreezerModel, tracks: List<Track>, type: Int) {
    //Type: 0 = lastPlayed, 1 = favoriteTracks
    val state = rememberLazyListState()
    LazyRow(
        state = state,
    ) {
        items(tracks) { TrackPanel(model, it, type) }
    }
}

@Composable
private fun TrackPanel(model: FreezerModel, track: Track, type: Int) {
    //Type: 0 = lastPlayed, 1 = favoriteTracks
    with(model) {
        Column(
            content = {
                if (isLoadingImg) {
                    CircularProgressIndicator()
                } else {
                    when (type) {
                        0 -> {
                            if (lastPlayed.isNotEmpty()) {
                                if (lastPlayedCover[track.id] != null) {
                                    Image(
                                        bitmap = lastPlayedCover[track.id]!!,
                                        contentDescription = "Album Cover",
                                        modifier = Modifier
                                            .height(80.dp)
                                            .clip(CircleShape)
                                    )
                                } else {
                                    EmptyCircularCover()
                                }
                            }
                        }
                        1 -> {
                            if (favoriteTracks.isNotEmpty()) {
                                if (favoriteTracksCover[track.id] != null) {
                                    Image(
                                        bitmap = favoriteTracksCover[track.id]!!,
                                        contentDescription = "Album Cover",
                                        modifier = Modifier
                                            .height(80.dp)
                                            .clip(CircleShape)
                                    )
                                } else {
                                    EmptyCircularCover()
                                }
                            }
                        }
                    }
                }
                SingleLineTextBold(track.artist.name)
                SingleLineTextLight(track.title)
            },
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .width(140.dp)
                .height(140.dp)
                .padding(all = 4.dp)
                .clickable(onClick = {
                    getClickedTrackAsync(track.id, true)
                    currentScreen = Screen.PLAYER
                })
        )
    }
}