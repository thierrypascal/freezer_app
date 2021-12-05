package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.model.FreezerModel

@Composable
fun AlbumDetailScreen(model: FreezerModel) {
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
    StandardTopAppBar(model, false)
}

@Composable
private fun Body(model: FreezerModel) {
    val state = rememberLazyListState()
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Alle Songs zu Merkliste", "Alle Songs zu Warteliste")

    with(model) {
        Column(
            content = {
                Row(content = {
                    Column(content = {
                        TextBold(clickedAlbum.title)
                        Text("${clickedAlbum.nb_tracks} Lieder")
                        Text("${clickedAlbum.fans} Fans")
                        Text(clickedAlbum.label)
                        Text("Dauer: ${clickedAlbum.duration} min")
                        Text(clickedAlbum.release_date)
                    }, modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center)
                    Box(
                        content = {
                            if (isLoadingImg) {
                                CircularProgressIndicator()
                            } else {
                                if (clickedAlbumCover != null) {
                                    if (clickedAlbumCover != null) {
                                        Image(
                                            bitmap = clickedAlbumCover!!,
                                            contentDescription = "Album Cover",
                                            modifier = Modifier
                                                .padding(horizontal = 8.dp)
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(10.dp))
                                                .border(
                                                    2.dp,
                                                    MaterialTheme.colors.primary,
                                                    RoundedCornerShape(10.dp)
                                                )
                                        )
                                    } else {
                                        EmptyRoundedCoverBigDetail()
                                    }
                                }
                            }
                        }, modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(2f)
                    )
                })
                BasicRow(content = {
                    SingleLineTextBold("Lieder dieses Albums", 18.sp)
                    Box(content = {
                        IconButton(onClick = {
                            expanded = true
                        }) {
                            Icon(Icons.Outlined.Inventory2, "Optionen")
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
                                            favoriteTracks = favoriteTracks + clickedAlbumTracklist
                                        }
                                        1 -> {
                                            playlist = playlist + clickedAlbumTracklist
                                        }
                                    }
                                }) {
                                    Text(s)
                                }
                            }
                        }
                    })
                })
                if (clickedAlbumTracklist.isNotEmpty()) {
                    LazyColumn(
                        state = state,
                    ) {
                        items(clickedAlbumTracklist) { TrackListTile(model, it) }
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Diese Liste ist leer",
                        )
                    }
                }
            }, modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 60.dp)
                .fillMaxSize()
        )
    }
}

@Composable
private fun BottomBar(model: FreezerModel) {
    StandardAppBottomBar(model)
}
