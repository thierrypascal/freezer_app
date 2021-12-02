package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.data.classes.Album
import fhnw.emoba.freezerapp.data.classes.Artist
import fhnw.emoba.freezerapp.data.classes.Radio
import fhnw.emoba.freezerapp.data.classes.Track
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Tabs

@Composable
fun SearchScreen(model: FreezerModel) {
    val scaffoldState = rememberScaffoldState()

    with(model) {
        getRadiosAsync()
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
    val focusManager = LocalFocusManager.current
    with(model) {
        TopAppBar(
            title = { Text(text = "" /*TODO: add golden circle*/) },
            navigationIcon = { BackIcon(model) },
            actions = {
                TextField(
                    value = searchString,
                    onValueChange = { searchString = it },
                    label = { Text("Suchen") },
                    singleLine = true,
                    keyboardActions = KeyboardActions(onDone = {
                        getSearchAsync()
                        focusManager.clearFocus()
                    }),
                    trailingIcon = { Icon(Icons.Filled.Search, "Suchen") },
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        unfocusedIndicatorColor = Color.Transparent,
                        textColor = MaterialTheme.colors.primary,
                    ),
                )
            },
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.secondary,
            elevation = 0.dp,
        )
    }
}

@Composable
private fun Body(model: FreezerModel) {
    with(model) {
        Column(content = {
            TabRow(selectedTabIndex = currentTab.ordinal) {
                Tabs.values().forEach {
                    Tab(
                        selected = it == currentTab,
                        onClick = {
                            currentTab = it
                        },
                        text = { Text(it.title) }
                    )
                }
            }
            //TODO: list with type of list given by tabs.type -> switch for correct model.list
            val state = rememberLazyListState()
            //TODO: what happens when scrolled to the end of the list? loading next page of request?
            when (currentTab.type) {
                0 -> {
                    if (tracksFound.isNotEmpty()) {
                        LazyColumn(
                            state = state,
                        ) {
                            items(tracksFound) { ListTile(model, it, 0) }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Suche bitte zuerst nach einem Song",
                            )
                        }
                    }
                }
                1 -> {
                    if (artistsFound.isNotEmpty()) {
                        LazyColumn(
                            state = state,
                        ) {
                            items(artistsFound) { ListTile(model, it, 1) }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Suche bitte zuerst nach einem Künstler",
                            )
                        }
                    }
                }
                2 -> {
                    if (albumsFound.isNotEmpty()) {
                        LazyColumn(
                            state = state,
                        ) {
                            items(albumsFound) { ListTile(model, it, 2) }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Suche bitte zuerst nach einem Album",
                            )
                        }
                    }
                }
                3 -> {
                    if (radiosFound.isNotEmpty()) {
                        LazyColumn(
                            state = state,
                        ) {
                            items(radiosFound) { ListTile(model, it, 3) }
                        }
                    } else {
                        //TODO: Radio seems to just be a list, no searching required?
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Suche bitte zuerst nach einem Radio",
                            )
                        }
                    }
                }
            }
        }, modifier = Modifier.padding(bottom = 60.dp))
    }
}

@Composable
private fun ListTile(model: FreezerModel, element: Any, type: Int) {
    with(model) {
        when (type) {
            0 -> {
                val track = element as Track
                Row(
                    content = {
                        Row(content = {
                            Text(
                                text = track.title.filterNot { it.isWhitespace() }
                                    .substring(startIndex = 0, endIndex = 2),
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.width(40.dp)
                            )
                            Column(
                                content = {
                                    SingleLineTextBold(text = track.title)
                                    SingleLineTextLight(text = track.artist.name)
                                },
                                modifier = Modifier
                                    .width(getScreenWidth().dp - 150.dp)
                                    .clickable(onClick = {/*TODO: play track*/ })
                            )
                        })
                        IconButton(onClick = { /*TODO: open Popup with option: add to playlist, add to favoriteTracks, show more Info*/ }) {
                            Icon(Icons.Filled.MoreVert, "Optionen")
                        }
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }
            1 -> {
                val artist = element as Artist
                Row(
                    content = {
                        Row(content = {
                            Text(
                                text = artist.name.filterNot { it.isWhitespace() }
                                    .substring(startIndex = 0, endIndex = 2),
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.width(40.dp)
                            )
                            Column(
                                content = {
                                    SingleLineTextBold(text = artist.name)
                                    SingleLineTextLight(text = "${0/*TODO: artist.tracklist.size()*/} Songs")
                                },
                                modifier = Modifier
                                    .width(getScreenWidth().dp - 150.dp)
                                    .clickable(onClick = {/*TODO: show more info about artist with show tracklist*/ })
                            )
                        })
                        IconButton(onClick = { /*TODO: open Popup with option: add tracklist to playlist, add to tracklist favoriteTracks, show more Info*/ }) {
                            Icon(Icons.Filled.MoreVert, "Optionen")
                        }
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }
            2 -> {
                val album = element as Album
                Row(
                    content = {
                        Row(content = {
                            Text(
                                text = album.title.filterNot { it.isWhitespace() }
                                    .substring(startIndex = 0, endIndex = 2),
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.width(40.dp)
                            )
                            Column(
                                content = {
                                    SingleLineTextBold(text = album.title)
                                    SingleLineTextLight(text = "${0/*TODO: album.tracklist.size()*/} Songs")
                                },
                                modifier = Modifier
                                    .width(getScreenWidth().dp - 150.dp)
                                    .clickable(onClick = {/*TODO: show more info about album and artist with show tracklist*/ })
                            )
                        })
                        IconButton(onClick = { /*TODO: open Popup with option: add tracklist to playlist, add to tracklist favoriteTracks, show more Info*/ }) {
                            Icon(Icons.Filled.MoreVert, "Optionen")
                        }
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }
            3 -> {
                val radio = element as Radio
                Row(
                    content = {
                        Row(content = {
                            Text(
                                text = radio.title.filterNot { it.isWhitespace() }
                                    .substring(startIndex = 0, endIndex = 2),
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.width(40.dp)
                            )
                            Column(
                                content = {
                                    SingleLineTextBold(text = radio.title)
                                    SingleLineTextLight(text = "${0/*TODO: radio.tracklist.size()*/} Songs")
                                },
                                modifier = Modifier
                                    .width(getScreenWidth().dp - 150.dp)
                                    .clickable(onClick = {/*TODO: show more info about radio with show tracklist*/ })
                            )
                        })
                        IconButton(onClick = { /*TODO: open Popup with option: add tracklist to playlist, add to tracklist favoriteTracks, show more Info*/ }) {
                            Icon(Icons.Filled.MoreVert, "Optionen")
                        }
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }
        }
        Divider()
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
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.secondary,
            elevation = 0.dp,
        )
    }
}
