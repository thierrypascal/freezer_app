package fhnw.emoba.freezerapp.model

import android.content.res.Resources
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import fhnw.emoba.freezerapp.data.classes.*
import fhnw.emoba.freezerapp.data.service.FreezerService
import kotlinx.coroutines.*
import kotlin.collections.HashMap

class FreezerModel(val service: FreezerService) {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    var currentScreen by mutableStateOf(Screen.HOME)
    var currentTab by mutableStateOf(Tabs.TRACK)
    var currentlyPlaying by mutableStateOf(Track())
    var currentlyPlayingCover: ImageBitmap? by mutableStateOf(null)

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
    var clickedArtistPicture: ImageBitmap? by mutableStateOf(null)
    var clickedArtistTracklist: List<Track> by mutableStateOf(emptyList())
    var clickedAlbum: Album by mutableStateOf(Album())
    var clickedAlbumCover: ImageBitmap? by mutableStateOf(null)
    var clickedAlbumTracklist: List<Track> by mutableStateOf(emptyList())
    var clickedRadio: Radio by mutableStateOf(Radio())
    var clickedRadioPicture: ImageBitmap? by mutableStateOf(null)
    var clickedRadioTracklist: List<Track> by mutableStateOf(emptyList())

    var favoriteTracksCover: Map<Int, ImageBitmap?> = mutableMapOf()
    var lastPlayedCover: Map<Int, ImageBitmap?> = mutableMapOf()

    var isLoading by mutableStateOf(false)
    var isLoadingImg by mutableStateOf(false)

    var playerIsReady by mutableStateOf(true)
    private var currentlyPlayingId = 0  // wird nur intern gebraucht, soll kein Recompose ausloesen, daher auch kein MutableState

    private val player = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        setOnPreparedListener(MediaPlayer.OnPreparedListener {
            start()
        })
        setOnCompletionListener {
            playerIsReady = true
        }
    }

    //music player functions
    fun startPlayer(){
        playerIsReady = false
        try {
            if (currentlyPlayingId == currentlyPlaying.id && !player.isPlaying) {
                player.start()
            } else {
                currentlyPlayingId = currentlyPlaying.id
                player.reset()
                player.setDataSource(currentlyPlaying.preview)
                player.prepareAsync()
            }
        } catch (e: Exception) {
            playerIsReady = true
            println("Player couldn't start: $e")
        }
    }

    fun pausePlayer() {
        player.pause()
        playerIsReady = true
    }

    fun fromStart() {
        player.seekTo(0)
        player.start()
        playerIsReady = false
    }

    fun nextTrack() {
        if (playlist.isNotEmpty()){
            modelScope.launch {
                getClickedTrackAsync(playlist[0].id, true)

                //TODO: find a better solution
                delay(100)

                currentlyPlayingId = playlist[0].id
                player.reset()
                player.setDataSource(currentlyPlaying.preview)
                player.prepareAsync()
                playerIsReady = false

                playlist = playlist - playlist[0]
            }
        }
    }

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

    fun getClickedTrackAsync(searchFilter: Int, shouldPlay: Boolean) {
        isLoading = true
        clickedTrack = Track()
        modelScope.launch {
            clickedTrack = service.requestTrack(searchFilter)
            isLoading = false
            if (shouldPlay){
                currentlyPlaying = clickedTrack
                if (!lastPlayed.map { t -> t.id }.contains(currentlyPlaying.id)){
                    lastPlayed = lastPlayed + currentlyPlaying
                }
                getCurrentlyPlayingCoverAsync()
            }
        }
    }

    fun getClickedArtistAsync(searchFilter: Int) {
        isLoading = true
        clickedArtist = Artist()
        clickedArtistTracklist = emptyList()
        modelScope.launch {
            clickedArtist = service.requestArtist(searchFilter)
            clickedArtistTracklist = service.requestTracklist(clickedArtist.tracklist)
            isLoading = false

            getClickedArtistPictureAsync()
        }
    }

    fun getClickedArtistPictureAsync(){
        if (clickedArtist != null && clickedArtist != Artist()){
            isLoadingImg = true
            modelScope.launch {
                clickedArtistPicture = service.requestCover(clickedArtist.picture, "xl")
                isLoadingImg = false
            }
        }
    }

    fun getClickedAlbumAsync(searchFilter: Int) {
        isLoading = true
        clickedAlbum = Album()
        clickedAlbumTracklist = emptyList()
        modelScope.launch {
            clickedAlbum = service.requestAlbum(searchFilter)
            clickedAlbumTracklist = service.requestTracklist(clickedAlbum.tracklist)
            isLoading = false

            getClickedAlbumCoverAsync()
        }
    }

    fun getClickedAlbumCoverAsync(){
        if (clickedAlbum != null && clickedAlbum != Album()){
            isLoadingImg = true
            modelScope.launch {
                clickedAlbumCover = service.requestCover(clickedAlbum.cover, "xl")
                isLoadingImg = false
            }
        }
    }

    fun getClickedRadioAsync(searchFilter: Int) {
        isLoading = true
        clickedRadio = Radio()
        clickedRadioTracklist = emptyList()
        modelScope.launch {
            clickedRadio = service.requestRadioById(searchFilter)
            clickedRadioTracklist = service.requestTracklist(clickedRadio.tracklist)
            isLoading = false

            getClickedRadioPictureAsync()
        }
    }

    fun getClickedRadioPictureAsync(){
        if (clickedRadio != null && clickedRadio != Radio()){
            isLoadingImg = true
            modelScope.launch {
                clickedRadioPicture = service.requestCover(clickedRadio.picture, "xl")
                isLoadingImg = false
            }
        }
    }

    fun getRadiosAsync() {
        isLoading = true
        radiosFound = emptyList()
        modelScope.launch {
            radiosFound = service.requestRadio()
            radiosFound = radiosFound.sortedBy { radio -> radio.title }
            isLoading = false
        }
    }

    fun getCurrentlyPlayingCoverAsync(){
        if (currentlyPlaying != null && currentlyPlaying != Track()){
            isLoadingImg = true
            modelScope.launch {
                currentlyPlayingCover = service.requestCover(currentlyPlaying.album.cover, "xl")
                isLoadingImg = false
            }
        }
    }

    fun getCoverAsync() {
        if (favoriteTracks.isNotEmpty()){
            isLoadingImg = true
            modelScope.launch {
                favoriteTracks.forEach{t ->
                    val coverMap: HashMap<Int, ImageBitmap?> = hashMapOf(
                        t.id to service.requestCover(t.album.cover, "big"),
                    )
                    favoriteTracksCover = favoriteTracksCover + coverMap
                }
                isLoadingImg = false
            }
        }

        if (lastPlayed.isNotEmpty()){
            isLoadingImg = true
            modelScope.launch {
                lastPlayed.forEach{t ->
                    val coverMap: HashMap<Int, ImageBitmap?> = hashMapOf(
                        t.id to service.requestCover(t.album.cover, "big"),
                    )
                    lastPlayedCover = lastPlayedCover + coverMap
                }
                isLoadingImg = false
            }
        }
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.densityDpi
    }
}