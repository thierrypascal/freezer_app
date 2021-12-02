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
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.model.FreezerModel

@Composable
fun RadioDetailScreen(model: FreezerModel) {
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
                Row(content = {
                    Column(content = {
                        TextBold(clickedRadio.title)
                    }, modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center)
                    Box(
                        content = {
                            if (isLoadingImg) {
                                CircularProgressIndicator()
                            } else {
                                if (clickedRadioPicture != null) {
                                    if (clickedRadioPicture != null) {
                                        Image(
                                            bitmap = clickedRadioPicture!!,
                                            contentDescription = "Radio Bild",
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
                SingleLineTextBold("Lieder dieses Radios", 18.sp)
                if (clickedRadioTracklist.isNotEmpty()) {
                    LazyColumn(
                        state = state,
                    ) {
                        items(clickedRadioTracklist) { TrackListTile(model, it) }
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
