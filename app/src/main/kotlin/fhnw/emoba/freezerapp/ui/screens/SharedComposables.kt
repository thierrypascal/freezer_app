package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.material.icons.outlined.Stop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
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
fun SingleLineTextBold(text: String, fontSize: TextUnit = 16.sp) {
    Text(
        text,
        overflow = TextOverflow.Clip,
        softWrap = true,
        textAlign = TextAlign.Center,
        maxLines = 1,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
    )
}

@Composable
fun SingleLineTextLight(text: String, fontSize: TextUnit = 14.sp) {
    Text(
        text = text,
        overflow = TextOverflow.Clip,
        softWrap = true,
        textAlign = TextAlign.Center,
        maxLines = 1,
        fontWeight = FontWeight.Light,
        fontSize = fontSize
    )
}

@Composable
fun StandardAppBottomBar(model: FreezerModel) {
    BottomAppBar(
        content = {
            IconButton(onClick = { model.currentScreen = Screen.PLAYLIST }) {
                Icon(Icons.Filled.QueueMusic, "Playlist")
            }
            MiniPlayer(model)
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.secondary,
        elevation = 0.dp,
    )
}

@Composable
fun MiniPlayer(model: FreezerModel) {
    with(model) {
        Row(
            content = {
                IconButton(
                    onClick = { fromStart() },
                    modifier = Modifier.size(60.dp),
                    enabled = currentlyPlaying.id != 0,
                ) {
                    Icon(
                        Icons.Outlined.SkipPrevious,
                        "Zum Anfang",
                        tint = if (currentlyPlaying.id != 0) MaterialTheme.colors.primary else Color.DarkGray,
                        modifier = Modifier.size(30.dp)
                    )
                }
                if (playerIsReady) {
                    IconButton(
                        onClick = { startPlayer() },
                        modifier = Modifier.size(60.dp),
                        enabled = currentlyPlaying.id != 0,
                    ) {
                        Icon(
                            Icons.Outlined.PlayArrow,
                            "Abspielen",
                            tint = if (currentlyPlaying.id != 0) MaterialTheme.colors.primary else Color.DarkGray,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { pausePlayer() },
                        modifier = Modifier.size(60.dp),
                        enabled = currentlyPlaying.id != 0,
                    ) {
                        Icon(
                            Icons.Outlined.Stop,
                            "Abspielen",
                            tint = if (currentlyPlaying.id != 0) MaterialTheme.colors.primary else Color.DarkGray,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                IconButton(
                    onClick = { nextTrack() },
                    modifier = Modifier.size(60.dp),
                    enabled = currentlyPlaying.id != 0,
                ) {
                    Icon(
                        Icons.Outlined.SkipNext,
                        "Nächstes Lied",
                        tint = if (playlist.isNotEmpty() && currentlyPlaying.id != 0) MaterialTheme.colors.primary else Color.DarkGray,
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.Center
        )
    }
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

@Composable
fun EmptyCircularCoverBigPlayer() {
    Image(
        painter = painterResource(R.drawable.no_image),
        contentDescription = "Album Cover nicht verfügbar",
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colors.primary, CircleShape)
    )
}

@Composable
fun EmptyRoundedCoverBigDetail() {
    Image(
        painter = painterResource(R.drawable.no_image),
        contentDescription = "Bild nicht verfügbar",
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, MaterialTheme.colors.primary, RoundedCornerShape(10.dp))
    )
}