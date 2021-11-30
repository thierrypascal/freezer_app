package fhnw.emoba.freezerapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.classes.Album
import fhnw.emoba.freezerapp.data.classes.Artist
import fhnw.emoba.freezerapp.data.classes.Radio
import fhnw.emoba.freezerapp.data.classes.Track
import fhnw.emoba.freezerapp.data.service.FreezerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FreezerModel(val service: FreezerService) {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    var currentScreen by mutableStateOf(Screen.HOME)

    val title = "Freezer"
    val subtitle = "exclusive version"
    var searchStringTrack by mutableStateOf("")
    var searchStringArtist by mutableStateOf("")
    var searchStringAlbum by mutableStateOf("")

    //List of last played tracks
    var lastPlayed: List<Track> by mutableStateOf(emptyList())
    //List of favorite tracks
    var favoriteTrack: List<Track> by mutableStateOf(emptyList())
    //Lazy List of all? tracks
    var tracksFound: List<Track> by mutableStateOf(emptyList())
    //Lazy List of all? artists
    var artistsFound: List<Artist> by mutableStateOf(emptyList())
    //Lazy List of all? albums
    var albumsFound: List<Album> by mutableStateOf(emptyList())
    //Lazy List of all? radios
    var radiosFound: List<Radio> by mutableStateOf(emptyList())

    var isLoading by mutableStateOf(false)

    //TODO: load all lists
    fun getTracksAsync(){
        isLoading = true
        tracksFound = emptyList()
        modelScope.launch {
            tracksFound = service.requestTrack(searchStringTrack)
            isLoading = false
        }
    }

    fun getArtistsAsync(){
        isLoading = true
        artistsFound = emptyList()
        modelScope.launch {
            artistsFound = service.requestArtist(searchStringArtist)
            isLoading = false
        }
    }

    fun getAlbumsAsync(){
        isLoading = true
        albumsFound = emptyList()
        modelScope.launch {
            albumsFound = service.requestAlbum(searchStringAlbum)
            isLoading = false
        }
    }

    fun getRadiosAsync(){
        //TODO: search radio?
        isLoading = true
        radiosFound = emptyList()
        modelScope.launch {
            radiosFound = service.requestRadio()
            isLoading = false
        }
    }
}