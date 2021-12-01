package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.time.format.TextStyle

@Composable
fun SearchIcon(){
    IconButton(onClick = { /*TODO route to SearchPage*/ }) {
        Icon(Icons.Filled.Search, "Suchen")
    }
}

@Composable
fun BackIcon(){
    IconButton(onClick = { /*TODO route to last screen*/ }) {
        Icon(Icons.Filled.ArrowBackIos, "Zurück")
    }
}

@Composable
fun BasicRow(content: @Composable RowScope.() -> Unit) {
    Row(modifier              = Modifier.fillMaxWidth(),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content               = content)
}

@Composable
fun SingleLineTextBold(text: String){
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
fun SingleLineText(text: String){
    Text(
        text = text,
        overflow = TextOverflow.Clip,
        softWrap = true,
        textAlign = TextAlign.Center,
        maxLines = 1,
    )
}

@Composable
fun GoldenCircle(){
    //TODO: implement GoldenCircle
}