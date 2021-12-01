package fhnw.emoba.freezerapp.data.service

import fhnw.emoba.freezerapp.data.classes.*
import fhnw.emoba.freezerapp.data.content
import org.json.JSONObject
import java.lang.Exception

class FreezerService {
    //TODO: load classes with data from deezer api
    private val baseURL = "https://api.deezer.com/"
    private val appendRadioUrl = "radio"
    private val appendSearchUrl = "search?q="
    private val appendTrackUrl = "track/"
    private val appendAlbumUrl = "album/"
    private val appendArtistUrl = "artist/"

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
            //TODO: handle Exception correctly
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
            //TODO: handle Exception correctly
            println("$url: $e")
            return Track()
        }
    }

    fun requestArtist(searchFilter: Int): Artist{
        val url = "$baseURL$appendArtistUrl$searchFilter"
        try {
            val data = JSONObject(content(url))
            return Artist(data)
        } catch (e: Exception) {
            //TODO: handle Exception correctly
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
            //TODO: handle Exception correctly
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
            //TODO: handle Exception correctly
            println("$url: $e")
            return emptyList()
        }
    }
}