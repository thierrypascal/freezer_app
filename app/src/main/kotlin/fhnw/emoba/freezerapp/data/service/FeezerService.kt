package fhnw.emoba.freezerapp.data.service

import fhnw.emoba.freezerapp.data.classes.Track
import fhnw.emoba.freezerapp.data.content
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class FeezerService {
    //TODO: load classes with data from deezer api
    val baseURL = "https://api.deezer.com/"
    val appendSearchUrl = "search?q="
    val searchForTrackUrl = "track:"
    val appendRadioUrl = "radio"
    val appendAlbumUrl = "album:"
    val appendArtistUrl = "artist:"

    fun requestTrack(searchFilter: String): List<Track>{
        val url = "$baseURL$appendSearchUrl$searchForTrackUrl\"$searchFilter\""
        println("Searching for: $url")
        try {
            val data = JSONObject(content(url))
            val trackData = data.getJSONArray("data")
            println("trackData: ${trackData.getJSONObject(0)}")
            val list: MutableList<Track> = mutableListOf()
            for (i in 0 until trackData.length()) {
                list.add(Track(trackData.getJSONObject(i)))
            }
            println("L0${list[0].id}")
            println("L1${list[1].id}")
            return list
        }catch (e: Exception){
            //TODO: handle Exception correctly
            println("Exception: ${e.message}")
            return emptyList()
        }
    }
}