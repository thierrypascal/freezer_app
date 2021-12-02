package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.lang.Exception
import java.net.URL

data class Album(
    val id: Int,
    val title: String,
    val cover: String,

    val label: String,
    val nb_tracks: Int,
    val link: URL,
    val share: URL,
    val duration: Int,
    val fans: Int,
    val release_date: String,
    val artist: Artist,

    val tracklist: String
) {
    constructor(jsonObject: JSONObject) : this(
        id =            jsonObject.getInt("id"),
        title =         jsonObject.getString("title").trim(),
        cover =         jsonObject.getString("cover"),

        label =         try {jsonObject.getString("label")}    catch (e: Exception) {""},
        nb_tracks =      try {jsonObject.getInt("nb_tracks")}    catch (e: Exception) {0},
        link =          try {URL(jsonObject.getString("link"))}    catch (e: Exception) {URL("https://google.com/doesntexist")},
        share =         try {URL(jsonObject.getString("share"))}    catch (e: Exception) {URL("https://google.com/doesntexist")},
        duration =      try {jsonObject.getInt("duration")}    catch (e: Exception) {0},
        fans =      try {jsonObject.getInt("fans")}    catch (e: Exception) {0},
        release_date =  try {jsonObject.getString("release_date")}  catch (e: Exception) {"NaN"}, //TODO: to DateTime
        artist =        try {Artist(jsonObject.getJSONObject("artist"))}    catch (e: Exception) {Artist()},

        tracklist =     jsonObject.getString("tracklist"),
    )

    constructor() : this(
        id =            0,
        title =         "",
        cover =         "https://google.com/doesntexist",

        label =         "",
        nb_tracks =     0,
        link =          URL("https://google.com/doesntexist"),
        share =         URL("https://google.com/doesntexist"),
        duration =      0,
        fans =          0,
        release_date =  "", //TODO: to DateTime
        artist =        Artist(),

        tracklist =     "",
    )

    override fun toString(): String {
        return "Track(id='$id', title='$title')"
    }
}