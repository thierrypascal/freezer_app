package fhnw.emoba.freezerapp.model

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import fhnw.emoba.freezerapp.data.classes.*
import fhnw.emoba.freezerapp.data.service.FreezerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FreezerModel(val service: FreezerService) {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    var currentScreen by mutableStateOf(Screen.HOME)
    var currentTab by mutableStateOf(Tabs.TRACK)

    val title = "Freezer"
    val subtitle = "exclusive version"
    var searchString by mutableStateOf("")

    var playlist: List<Track> by mutableStateOf(emptyList())
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

    var favoriteTracksCover: Map<Int, ImageBitmap?> = mutableMapOf()

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
                artistsFound = searchResult.map { it.artist }.distinctBy { artist -> artist.id }
                albumsFound = searchResult.map { it.album }.distinctBy { album -> album.id }

                tracksFound = tracksFound.sortedBy { it.title }
                artistsFound = artistsFound.sortedBy { it.name }
                albumsFound = albumsFound.sortedBy { it.title }
            }
            isLoading = false
        }
    }

    fun getClickedTrackAsync(searchFilter: Int) {
        isLoading = true
        clickedTrack = Track()
        modelScope.launch {
            clickedTrack = service.requestTrack(searchFilter)
            isLoading = false
        }
    }

    fun getClickedArtistAsync(searchFilter: Int) {
        isLoading = true
        clickedArtist = Artist()
        modelScope.launch {
            clickedArtist = service.requestArtist(searchFilter)
            isLoading = false
        }
    }

    fun getClickedAlbumAsync(searchFilter: Int) {
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
            radiosFound = radiosFound.sortedBy { radio -> radio.title }
            isLoading = false
        }
    }

    fun getCoverAsync() {
        if (favoriteTracks.isNotEmpty()){
            isLoadingImg = true
            modelScope.launch {
                favoriteTracks.forEach{t ->
                    val coverMap: HashMap<Int, ImageBitmap?> = hashMapOf(
                        t.id to service.requestCover(t, "big"),
                    )
                    favoriteTracksCover = favoriteTracksCover + coverMap
                }
                isLoadingImg = false
            }
        }
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.densityDpi
    }
}