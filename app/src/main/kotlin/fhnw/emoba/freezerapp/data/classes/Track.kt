package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.lang.Exception
import java.net.URL

data class Track(
    val id: Int,
    val title: String,
    val title_short: String,
    val link: URL,
    val duration: Int,
    val explicit_lyrics: Boolean,
    val preview: String,
    val artist: Artist,
    val album: Album,

    val share: URL,
    val track_position: Int,
    val release_date: String,
) {
    constructor(jsonObject: JSONObject) : this(
        id =                        jsonObject.getInt("id"),
        title =                     jsonObject.getString("title").trim(),
        title_short =               jsonObject.getString("title_short").trim(),
        duration =                  jsonObject.getInt("duration"),
        explicit_lyrics =           jsonObject.getBoolean("explicit_lyrics"),
        preview =                   jsonObject.getString("preview"),
        artist =                    Artist(jsonObject.getJSONObject("artist")),
        album =                     Album(jsonObject.getJSONObject("album")),

        link =              try {URL(jsonObject.getString("link"))}     catch (e: Exception) {URL("https://google.com/doesntexist")},
        share =             try {URL(jsonObject.getString("share"))}    catch (e: Exception) {URL("https://google.com/doesntexist")},
        track_position =    try {jsonObject.getInt("track_position")}   catch (e: Exception) {0},
        release_date =      try {jsonObject.getString("release_date")}  catch (e: Exception) {"NaN"},
    )

    //special constructor for tracklist of album
    constructor(jsonObject: JSONObject, album: Album) : this(
        id =                        jsonObject.getInt("id"),
        title =                     jsonObject.getString("title").trim(),
        title_short =               jsonObject.getString("title_short").trim(),
        duration =                  jsonObject.getInt("duration"),
        explicit_lyrics =           jsonObject.getBoolean("explicit_lyrics"),
        preview =                   jsonObject.getString("preview"),
        artist =                    Artist(jsonObject.getJSONObject("artist")),
        album =                     album,

        link =              try {URL(jsonObject.getString("link"))}     catch (e: Exception) {URL("https://google.com/doesntexist")},
        share =             try {URL(jsonObject.getString("share"))}    catch (e: Exception) {URL("https://google.com/doesntexist")},
        track_position =    try {jsonObject.getInt("track_position")}   catch (e: Exception) {0},
        release_date =      try {jsonObject.getString("release_date")}  catch (e: Exception) {"NaN"},
    )

    constructor() : this(
        id =                0,
        title =             "",
        title_short =       "",
        link =              URL("https://google.com/doesntexist"),
        duration =          0,
        explicit_lyrics =   true,
        preview =           "https://google.com/doesntexist",
        artist =            Artist(),
        album =             Album(),

        share =             URL("https://google.com/doesntexist"),
        track_position =    0,
        release_date =      "NaN",
    )

    override fun toString(): String {
        return "Track(id='$id', title='$title', link='$link')"
    }
}