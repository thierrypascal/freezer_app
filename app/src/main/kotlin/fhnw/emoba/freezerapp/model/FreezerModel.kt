package fhnw.emoba.freezerapp.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.R
import fhnw.emoba.freezerapp.data.classes.*
import fhnw.emoba.freezerapp.data.service.FreezerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection

import android.graphics.drawable.BitmapDrawable




class FreezerModel(val service: FreezerService) {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    var currentScreen by mutableStateOf(Screen.HOME)

    val title = "Freezer"
    val subtitle = "exclusive version"
    var searchString by mutableStateOf("")

    var lastPlayed: List<Track> by mutableStateOf(emptyList())
    var favoriteTracks: List<Track> by mutableStateOf(emptyList())
    var searchResult: List<Track> by mutableStateOf(emptyList())
    var tracksFound: List<Track> by mutableStateOf(emptyList())
    var artistsFound: List<Artist> by mutableStateOf(emptyList())
    var albumsFound: List<Album> by mutableStateOf(emptyList())
    var radiosFound: List<Radio> by mutableStateOf(emptyList())

    var clickedTrack: Track by mutableStateOf(Track())
    var clickedArtist: Artist by mutableStateOf(Artist())
    var clickedAlbum: Album by mutableStateOf(Album())

    var isLoading by mutableStateOf(false)
    var isLoadingImg by mutableStateOf(false)


    //load all lists
    fun getSearchAsync() {
        isLoading = true
        searchResult = emptyList()
        tracksFound = emptyList()
        artistsFound = emptyList()
        albumsFound = emptyList()
        modelScope.launch {
            searchResult = service.requestSearch(searchString)

            if (searchResult.isNotEmpty()) {
                tracksFound = searchResult
                artistsFound = searchResult.map { it.artist }
                albumsFound = searchResult.map { it.album }
            }
            isLoading = false
        }
    }

    fun getClickedTrackAsync(searchFilter: Int) {
        //TODO: search radio?
        isLoading = true
        clickedTrack = Track()
        modelScope.launch {
            clickedTrack = service.requestTrack(searchFilter)
            isLoading = false
        }
    }

    fun getClickedArtistAsync(searchFilter: Int) {
        //TODO: search radio?
        isLoading = true
        clickedArtist = Artist()
        modelScope.launch {
            clickedArtist = service.requestArtist(searchFilter)
            isLoading = false
        }
    }

    fun getClickedAlbumAsync(searchFilter: Int) {
        //TODO: search radio?
        isLoading = true
        clickedAlbum = Album()
        modelScope.launch {
            clickedAlbum = service.requestAlbum(searchFilter)
            isLoading = false
        }
    }

    fun getRadiosAsync() {
        //TODO: search radio?
        isLoading = true
        radiosFound = emptyList()
        modelScope.launch {
            radiosFound = service.requestRadio()
            isLoading = false
        }
    }

    fun getCoverAsync(track: Track, size: String): ImageBitmap? {
        //TODO: implement async request for img with simple caching
//        isLoading = true
//        var img: ImageBitmap? = null
//        modelScope.launch {
//            img = service.requestCover(track, size)
//            isLoading = false
//        }
        return null
    }
}