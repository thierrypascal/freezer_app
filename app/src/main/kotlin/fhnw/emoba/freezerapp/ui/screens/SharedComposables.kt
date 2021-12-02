package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.R
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen

@Composable
fun SearchIcon(model: FreezerModel) {
    IconButton(onClick = { model.currentScreen = Screen.SEARCH }) {
        Icon(Icons.Filled.Search, "Suchen")
    }
}

@Composable
fun BackIcon(model: FreezerModel) {
    IconButton(onClick = { model.currentScreen = Screen.HOME }) {
        Icon(Icons.Filled.ArrowBackIos, "Zurück")
    }
}

@Composable
fun BasicRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content = content
    )
}

@Composable
fun SingleLineTextBold(text: String) {
    Text(
        text,
        overflow = TextOverflow.Clip,
        softWrap = true,
        textAlign = TextAlign.Center,
        maxLines = 1,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun SingleLineTextLight(text: String) {
    Text(
        text = text,
        overflow = TextOverflow.Clip,
        softWrap = true,
        textAlign = TextAlign.Center,
        maxLines = 1,
        fontWeight = FontWeight.Light,
    )
}

@Composable
fun SingleLineText(text: String) {
    Text(
        text = text,
        overflow = TextOverflow.Clip,
        softWrap = true,
        textAlign = TextAlign.Center,
        maxLines = 1,
    )
}

@Composable
fun StandardAppBottomBar(model: FreezerModel) {
    BottomAppBar(
        content = {
            IconButton(onClick = { model.currentScreen = Screen.PLAYLIST }) {
                Icon(Icons.Filled.QueueMusic, "Playlist")
            }
            //TODO: add miniplayer of currentlyPlaying
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.secondary,
        elevation = 0.dp,
    )
}

@Composable
fun StandardTopAppBar(model: FreezerModel) {
    TopAppBar(
        title = { InofficialLogo(model) },
        navigationIcon = { BackIcon(model) },
        actions = { SearchIcon(model) },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.secondary,
        elevation = 0.dp,
    )
}

@Composable
fun InofficialLogo(model: FreezerModel) {
    with(model) {
        Column(content = {
            Text(
                text = subtitle,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .offset(0.dp, 10.dp),
                fontSize = 13.sp
            )
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.align(alignment = Alignment.Start)
            )
        }, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(140.dp))
    }
}

@Composable
fun EmptyCircularCover() {
    Image(
        painter = painterResource(R.drawable.no_image),
        contentDescription = "Album Cover nicht verfügbar",
        modifier = Modifier
            .height(80.dp)
            .clip(CircleShape)
    )
}