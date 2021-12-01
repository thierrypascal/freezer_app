package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.R
import fhnw.emoba.freezerapp.data.classes.Track
import fhnw.emoba.freezerapp.ui.theme.AppDarkColors

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
                    //TODO: change to lastPlayed
                    tracksFound.isEmpty() -> Text("Noch keine Tracks gespielt")
                    else -> HorizontalTrackList(tracksFound)
                }
                BasicRow(
                    content = {
                        Text("Merkliste", fontWeight = FontWeight.Bold)
                        IconButton(onClick = { /*TODO: open favoriteTracksScreen*/ }) {
                            Icon(Icons.Filled.ArrowForwardIos, "Merkliste")
                        }
                    },
                )
                //TODO: scrollable list of favoriteTracks
                BasicRow(
                    content = {
                        Text("Listensuche", fontWeight = FontWeight.Bold)
                        IconButton(onClick = { /*TODO: open SearchScreen*/ }) {
                            Icon(Icons.Filled.ArrowForwardIos, "Listensuche")
                        }
                    },
                )
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
private fun HorizontalTrackList(tracks: List<Track>) {
    val state = rememberLazyListState()
    LazyRow(
        state = state,
    ) {
        items(tracks) { TrackPanel(it) }
    }
}

@Composable
private fun TrackPanel(track: Track) {
    Column(content = {
//        Image(track.album.image)
        SingleLineText("${track.id}")
        SingleLineText(track.title)
//        Text(track.artist.name)
    },
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(20.dp))
            .width(140.dp)
            .height(120.dp)
            .background(color = MaterialTheme.colors.primary)
            .padding(all = 4.dp)
            .clickable(onClick = {/*TODO: open in player*/ })
    )
}