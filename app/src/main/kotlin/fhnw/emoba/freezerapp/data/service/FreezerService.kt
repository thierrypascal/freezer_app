package fhnw.emoba.freezerapp.data.service

import fhnw.emoba.freezerapp.data.classes.Album
import fhnw.emoba.freezerapp.data.classes.Artist
import fhnw.emoba.freezerapp.data.classes.Radio
import fhnw.emoba.freezerapp.data.classes.Track
import fhnw.emoba.freezerapp.data.content
import org.json.JSONObject
import java.lang.Exception

class FreezerService {
    //TODO: load classes with data from deezer api
    private val baseURL = "https://api.deezer.com/"
    private val appendRadioUrl = "radio"
    private val appendSearchUrl = "search?q="
    private val searchForTrackUrl = "track:"
    private val searchForAlbumUrl = "album:"
    private val searchForArtistUrl = "artist:"

    fun requestTrack(searchFilter: String): List<Track>{
        val url = "$baseURL$appendSearchUrl$searchForTrackUrl\"$searchFilter\""
        try {
            val data = JSONObject(content(url))
            val trackData = data.getJSONArray("data")
            val list: MutableList<Track> = mutableListOf()
            for (i in 0 until trackData.length()) {
                list.add(Track(trackData.getJSONObject(i)))
            }
            return list
        }catch (e: Exception){
            //TODO: handle Exception correctly
            println("Exception: ${e.message}")
            return emptyList()
        }
    }

    fun requestArtist(searchFilter: String): List<Artist>{
        val url = "$baseURL$appendSearchUrl$searchForArtistUrl\"$searchFilter\""
        try {
            val data = JSONObject(content(url))
            val trackData = data.getJSONArray("data")
            val list: MutableList<Artist> = mutableListOf()
            for (i in 0 until trackData.length()) {
                list.add(Artist(trackData.getJSONObject(i)))
            }
            return list
        }catch (e: Exception){
            //TODO: handle Exception correctly
            println("Exception: ${e.message}")
            return emptyList()
        }
    }

    fun requestAlbum(searchFilter: String): List<Album>{
        val url = "$baseURL$appendSearchUrl$searchForAlbumUrl\"$searchFilter\""
        try {
            val data = JSONObject(content(url))
            val trackData = data.getJSONArray("data")
            val list: MutableList<Album> = mutableListOf()
            for (i in 0 until trackData.length()) {
                list.add(Album(trackData.getJSONObject(i)))
            }
            return list
        }catch (e: Exception){
            //TODO: handle Exception correctly
            println("Exception: ${e.message}")
            return emptyList()
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
            //TODO: handle Exception correctly
            println("Exception: ${e.message}")
            return emptyList()
        }
    }

}