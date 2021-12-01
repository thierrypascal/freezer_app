package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.R
import fhnw.emoba.freezerapp.data.classes.Track

@Composable
fun HomeScreen(model: FreezerModel) {
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
    val gold = MaterialTheme.colors.primary
    with(model) {
        TopAppBar(
            title = { Text(text = "" /*TODO: add golden circle*/) },
            navigationIcon = { BackIcon() },
            actions = { SearchIcon() },
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.secondary,
        )
    }
}

@Composable
private fun Body(model: FreezerModel) {
    with(model) {
        Column(
            content = {
                BasicRow(
                    content = {
                        Text("Zuletzt gespielt", fontWeight = FontWeight.Bold)
                        IconButton(onClick = { /*TODO: open LastPlayedScreen*/ }) {
                            Icon(Icons.Filled.ArrowForwardIos, "Zuletzt gespielt")
                        }
                    },
                )
                when {
                    lastPlayed.isEmpty() -> Text("Noch keine Tracks gespielt")
                    else -> HorizontalTrackList(model, lastPlayed)
                }
                BasicRow(
                    content = {
                        Text("Merkliste", fontWeight = FontWeight.Bold)
                        IconButton(onClick = { /*TODO: open favoriteTracksScreen*/ }) {
                            Icon(Icons.Filled.ArrowForwardIos, "Merkliste")
                        }
                    },
                )
                when {
                    favoriteTracks.isEmpty() -> Text("Noch keine gemerkten Tracks")
                    else -> HorizontalTrackList(model, favoriteTracks)
                }
                BasicRow(
                    content = {
                        Text("Listensuche", fontWeight = FontWeight.Bold)
                        IconButton(onClick = { /*TODO: open SearchScreen*/ }) {
                            Icon(Icons.Filled.ArrowForwardIos, "Listensuche")
                        }
                    },
                )
                //TODO: add Spacer
                //TODO: tap on Logo to get "Impressum"/README as Scrollable Text
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
            },
            modifier = Modifier.padding(8.0.dp)
        )
    }
}

@Composable
private fun BottomBar(model: FreezerModel) {
    with(model) {
        BottomAppBar(
            content = {
                IconButton(onClick = { /*TODO: open lastPlayedScreen*/ }) {
                    Icon(Icons.Filled.QueueMusic, "Zuletzt gespielt")
                }
                //TODO: add miniplayer of currentlyPlaying
            },
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.secondary,
        )
    }
}

@Composable
private fun HorizontalTrackList(model: FreezerModel, tracks: List<Track>) {
    val state = rememberLazyListState()
    LazyRow(
        state = state,
    ) {
        items(tracks) { TrackPanel(model, it) }
    }
}

@Composable
private fun TrackPanel(model: FreezerModel, track: Track) {
    Column(
        content = {
            model.getCoverAsync(track, "small")?.also {
                Image(
                    bitmap = it,
                    contentDescription = "Album Cover",
                    modifier = Modifier
                        .height(80.dp)
                        .clip(CircleShape)
                )
            } ?: Image(
                painter = painterResource(R.drawable.no_image),
                contentDescription = "Album Cover nicht anzeigbar",
                modifier = Modifier
                    .height(80.dp)
                    .clip(CircleShape)
            )
            SingleLineTextBold(track.artist.name)
            SingleLineText(track.title)
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(20.dp))
            .width(140.dp)
            .height(160.dp)
            .padding(all = 4.dp)
            .clickable(onClick = {/*TODO: open in player*/ })
    )
}