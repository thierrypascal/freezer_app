package fhnw.emoba.freezerapp.data.service

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.freezerapp.data.classes.*
import fhnw.emoba.freezerapp.data.content
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class FreezerService {
    private val baseURL = "https://api.deezer.com/"
    private val appendSearchUrl = "search?q="
    private val appendTrackUrl = "track/"
    private val appendAlbumUrl = "album/"
    private val appendArtistUrl = "artist/"
    private val appendRadioUrl = "radio/top"
    private val appendRadioByIdUrl = "radio/"

    fun requestSearch(searchFilter: String): List<Track>{
        val url = "$baseURL$appendSearchUrl$searchFilter"
        try {
            val data = JSONObject(content(url))
            val trackData = data.getJSONArray("data")
            val list: MutableList<Track> = mutableListOf()
            for (i in 0 until trackData.length()) {
                list.add(Track(trackData.getJSONObject(i)))
//                println(i)
//                println(list[i].id)
//                println(list[i].artist.id)
//                println(list[i].album.id)
//                println("------------")
            }
            return list
        }catch (e: Exception){
            println("$url: $e")
            return emptyList()
        }
    }

    fun requestTrack(searchFilter: Int): Track{
        val url = "$baseURL$appendTrackUrl$searchFilter"
        try {
            val data = JSONObject(content(url))
            return Track(data)
        } catch (e: Exception) {
            println("$url: $e")
            return Track()
        }
    }

    fun requestTracklist(url: String): List<Track>{
        try {
            val data = JSONObject(content(url))
            val trackData = data.getJSONArray("data")
            val list: MutableList<Track> = mutableListOf()
            for (i in 0 until trackData.length()) {
                list.add(Track(trackData.getJSONObject(i)))
            }
            return list
        }catch (e: Exception){
            println("$url: $e")
            return emptyList()
        }
    }

    fun requestArtist(searchFilter: Int): Artist{
        val url = "$baseURL$appendArtistUrl$searchFilter"
        try {
            val data = JSONObject(content(url))
            return Artist(data)
        } catch (e: Exception) {
            println("$url: $e")
            return Artist()
        }
    }

    fun requestAlbum(searchFilter: Int): Album{
        val url = "$baseURL$appendAlbumUrl$searchFilter"
        try {
            val data = JSONObject(content(url))
            return Album(data)
        } catch (e: Exception) {
            println("$url: $e")
            return Album()
        }
    }

    fun requestRadio(): List<Radio>{
        val url = "$baseURL$appendRadioUrl"
        try {
            val data = JSONObject(content(url))
            val trackData = data.getJSONArray("data")
            val list: MutableList<Radio> = mutableListOf()
            for (i in 0 until trackData.length()) {
                list.add(Radio(trackData.getJSONObject(i)))
            }
            return list
        }catch (e: Exception){
            println("$url: $e")
            return emptyList()
        }
    }

    fun requestRadioById(searchFilter: Int): Radio{
        val url = "$baseURL$appendRadioByIdUrl$searchFilter"
        try {
            val data = JSONObject(content(url))
            return Radio(data)
        } catch (e: Exception) {
            println("$url: $e")
            return Radio()
        }
    }

    fun requestCover(path: String, size: String): ImageBitmap? {
        try {
            val url = URL("${path}?size=${size}")
            val conn = url.openConnection() as HttpsURLConnection
            conn.connect()

            val inputStream = conn.inputStream
            val allBytes = inputStream.readBytes()
            inputStream.close()

            val bitmap = BitmapFactory.decodeByteArray(allBytes, 0, allBytes.size)

            return bitmap.asImageBitmap()
        } catch (e: Exception) {
            println(e)
            return null
        }
    }
}