package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.R
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
    with(model) {
        TopAppBar(
            title = { Text(text = "" /*TODO: add golden circle*/) },
            navigationIcon = { BackIcon() },
            actions = { SearchIcon() },
            backgroundColor = Color.Transparent,
            contentColor = AppDarkColors.secondary,
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
                //TODO: scrollable list of lastPlayed
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
                //TODO: add Deezer Logo with golden line above?/below?, tap on Logo to get "Impressum"/README as Scrollable Text
                Image(
                    painter = painterResource(R.drawable.deezerlogo_white),
                    contentDescription = "Deezer Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
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
            contentColor = AppDarkColors.secondary,
        )
    }
}