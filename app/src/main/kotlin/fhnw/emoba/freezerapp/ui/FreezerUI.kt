package fhnw.emoba.freezerapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.model.FreezerModel


@Composable
fun FreezerUI(model : FreezerModel){
    with(model){
        Box(contentAlignment = Alignment.Center,
            modifier         = Modifier.fillMaxSize()
        ){
            OutlinedTextField(
                value = searchStringTrack,
                onValueChange = {searchStringTrack  = it},
                placeholder = { Text("Suche Track")},
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = {
                    getTracksAsync()
                })
            )
        }
    }
}
