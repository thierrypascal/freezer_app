package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

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
