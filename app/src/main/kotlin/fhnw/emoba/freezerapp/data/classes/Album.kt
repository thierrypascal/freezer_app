package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.lang.Exception
import java.net.URL

data class Album(
    val id: Int,
    val title: String,
    val cover: String,

    val link: URL,
    val share: URL,
    val duration: Int,
    val release_date: String,
    val artist: Artist,

//    val tracklist: List<Track>
) {
    constructor(jsonObject: JSONObject) : this(
        id =            jsonObject.getInt("id"),
        title =         jsonObject.getString("title").trim(),
        cover =         jsonObject.getString("cover"), //TODO: to Picture/Bitmap?

        link =          try {URL(jsonObject.getString("link"))}    catch (e: Exception) {URL("https://google.com/doesntexist")},
        share =         try {URL(jsonObject.getString("share"))}    catch (e: Exception) {URL("https://google.com/doesntexist")},
        duration =      try {jsonObject.getInt("duration")}    catch (e: Exception) {0},
        release_date =  try {jsonObject.getString("release_date")}  catch (e: Exception) {"NaN"}, //TODO: to DateTime
        artist =        try {Artist(jsonObject.getJSONObject("artist"))}    catch (e: Exception) {Artist()},

////        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
    )

    constructor() : this(
        id =            0,
        title =         "",
        cover =         "https://google.com/doesntexist", //TODO: to Picture/Bitmap?, no_image handling

        link =          URL("https://google.com/doesntexist"),
        share =         URL("https://google.com/doesntexist"),
        duration =      0,
        release_date =  "", //TODO: to DateTime
        artist =      Artist(),

////        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
    )

    override fun toString(): String {
        return "Track(id='$id', title='$title')"
    }
}