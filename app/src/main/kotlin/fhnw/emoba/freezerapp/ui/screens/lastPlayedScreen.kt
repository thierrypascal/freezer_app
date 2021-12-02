package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.data.classes.Track
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen

@Composable
fun LastPlayedScreen(model: FreezerModel) {
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
    val state = rememberLazyListState()
    with(model) {
        Column(
            content = {
                BasicRow(
                    content = {
                        SingleLineTextBold("Zuletzt gespielt", 18.sp)
                        IconButton(onClick = { lastPlayed = emptyList() }) {
                            Icon(Icons.Outlined.Delete, "Liste leeren")
                        }
                    },
                )
                Column(content = {
                    if (lastPlayed.isNotEmpty()) {
                        LazyColumn(
                            state = state,
                        ) {
                            items(lastPlayed) { ListTile(model, it) }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Diese Liste ist noch leer",
                            )
                        }
                    }
                })
            }, modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxSize()
        )
    }
}

@Composable
private fun ListTile(model: FreezerModel, track: Track) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Aus Liste löschen")
    with(model) {
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
                            .width(widthDp.dp - 150.dp)
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
                                    lastPlayed = lastPlayed - track
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
        Divider()
    }
}

@Composable
private fun BottomBar(model: FreezerModel) {
    StandardAppBottomBar(model)
}
