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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.R
import fhnw.emoba.freezerapp.data.classes.Track
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
                            Text("Zuletzt gespielt", fontWeight = FontWeight.Bold)
                            IconButton(onClick = { currentScreen = Screen.LASTPLAYED }) {
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
                            IconButton(onClick = { currentScreen = Screen.FAVORITETRACKS }) {
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
                })
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
    with(model) {
        Column(
            content = {
                //TODO: while loading?
                if (isLoadingImg){
                    CircularProgressIndicator()
                }else{
                    if (favoriteTracks.isNotEmpty()){
                        favoriteTracksCover[track.id]?.let {
                            Image(
                                bitmap = it,
                                contentDescription = "Album Cover",
                                modifier = Modifier
                                    .height(80.dp)
                                    .clip(CircleShape)
                            )
                        } ?: run {
                            Image(
                                painter = painterResource(R.drawable.no_image),
                                contentDescription = "Album Cover nicht verfügbar",
                                modifier = Modifier
                                    .height(80.dp)
                                    .clip(CircleShape)
                            )
                        }
                    }
                }
                SingleLineTextBold(track.artist.name)
                SingleLineTextLight(track.title)
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
}