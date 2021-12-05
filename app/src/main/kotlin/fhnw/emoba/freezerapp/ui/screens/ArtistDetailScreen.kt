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
fun ArtistDetailScreen(model: FreezerModel) {
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
                        TextBold(clickedArtist.name)
                        Text("${clickedArtist.nb_album} Alben")
                        Text("${clickedArtist.nb_fan} Fans")
                    }, modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center)
                    Box(
                        content = {
                            if (isLoadingImg) {
                                CircularProgressIndicator()
                            } else {
                                if (clickedArtistPicture != null) {
                                    if (clickedArtistPicture != null) {
                                        Image(
                                            bitmap = clickedArtistPicture!!,
                                            contentDescription = "Artist Bild",
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
                    SingleLineTextBold("Lieder dieses Künstlers", 18.sp)
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
                                            favoriteTracks = favoriteTracks + clickedArtistTracklist
                                        }
                                        1 -> {
                                            playlist = playlist + clickedArtistTracklist
                                        }
                                    }
                                }) {
                                    Text(s)
                                }
                            }
                        }
                    })
                })
                if (clickedArtistTracklist.isNotEmpty()) {
                    LazyColumn(
                        state = state,
                    ) {
                        items(clickedArtistTracklist) { TrackListTile(model, it) }
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
