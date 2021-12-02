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
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
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
import fhnw.emoba.freezerapp.model.Screen
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
            title = { Text("") },
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
    val state = rememberLazyListState()
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
            //TODO: what happens when scrolled to the end of the list? loading next page of request?
            when (currentTab.type) {
                0 -> {
                    if (tracksFound.isNotEmpty()) {
                        LazyColumn(
                            state = state,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxSize()
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
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxSize()
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
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxSize()
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
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxSize()
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
        var expanded by remember { mutableStateOf(false) }
        when (type) {
            0 -> {
                val track = element as Track
                val items = listOf("Details", "Zu Merkliste", "Zu Warteliste")
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
                                    .clickable(onClick = {
                                        getClickedTrackAsync(track.id, true)
                                        currentScreen = Screen.PLAYER
                                    })
                            )
                        })
                        IconButton(onClick = {
                            expanded = true
                        }) {
                            Icon(Icons.Filled.MoreVert, "Optionen")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items.forEachIndexed { index, s ->
                                DropdownMenuItem(onClick = {
                                    expanded = false
                                    //TODO: show snackbar?
                                    when (index) {
                                        0 -> {
                                            //TODO: request and redirect to detailPage
                                        }
                                        1 -> {
                                            if (!favoriteTracks.contains(track)){
                                                favoriteTracks = favoriteTracks + track
                                            }
                                        }
                                        2 -> {
                                            playlist = playlist + track
                                        }
                                    }
                                }) {
                                    Text(s)
                                }
                            }
                        }
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }
            1 -> {
                val artist = element as Artist
                val items = listOf("Details", "Songs zu Merkliste", "Songs zu Warteliste")
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
                                    .clickable(onClick = {
                                        getClickedArtistAsync(artist.id)
                                        currentScreen = Screen.ARTISTDETAIL
                                    })
                            )
                        })
                        IconButton(onClick = {
                            expanded = true
                        }) {
                            Icon(Icons.Filled.MoreVert, "Optionen")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items.forEachIndexed { index, s ->
                                DropdownMenuItem(onClick = {
                                    expanded = false
                                    //TODO: show snackbar?
                                    when (index) {
                                        0 -> {
                                            //TODO: request and redirect to detailPage
                                        }
                                        1 -> {
                                            //TODO: favoriteTracks = favoriteTracks + artist.tracklist
                                        }
                                        2 -> {
                                            //TODO: playlist = playlist + artist.tracklist
                                        }
                                    }
                                }) {
                                    Text(s)
                                }
                            }
                        }
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }
            2 -> {
                val album = element as Album
                val items = listOf("Details", "Songs zu Merkliste", "Songs zu Warteliste")
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
                        IconButton(onClick = {
                            expanded = true
                        }) {
                            Icon(Icons.Filled.MoreVert, "Optionen")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items.forEachIndexed { index, s ->
                                DropdownMenuItem(onClick = {
                                    expanded = false
                                    //TODO: show snackbar?
                                    when (index) {
                                        0 -> {
                                            //TODO: request and redirect to detailPage
                                        }
                                        1 -> {
                                            //TODO: favoriteTracks = favoriteTracks + album.tracklist
                                        }
                                        2 -> {
                                            //TODO: playlist = playlist + album.tracklist
                                        }
                                    }
                                }) {
                                    Text(s)
                                }
                            }
                        }
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }
            3 -> {
                val radio = element as Radio
                val items = listOf("Details", "Songs zu Merkliste", "Songs zu Warteliste")
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
                        IconButton(onClick = {
                            expanded = true
                        }) {
                            Icon(Icons.Filled.MoreVert, "Optionen")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items.forEachIndexed { index, s ->
                                DropdownMenuItem(onClick = {
                                    expanded = false
                                    //TODO: show snackbar?
                                    when (index) {
                                        0 -> {
                                            //TODO: request and redirect to detailPage
                                        }
                                        1 -> {
                                            //TODO: favoriteTracks = favoriteTracks + radio.tracklist
                                        }
                                        2 -> {
                                            //TODO: playlist = playlist + radio.tracklist
                                        }
                                    }
                                }) {
                                    Text(s)
                                }
                            }
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
        StandardAppBottomBar(model)
    }
}
