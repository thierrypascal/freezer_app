package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.net.URL

data class Album(
    val id: Int,
    val title: String,
//    val link: URL,
//    val share: URL,
//    val duration: Int,
//    val release_date: String,
    val cover: URL,
//    val tracklist: List<Track>
//    val artist: Artist,
) {
    constructor(jsonObject: JSONObject) : this(
        id =                jsonObject.getInt("id"),
        title =              jsonObject.getString("title"),
//        link =      URL(    jsonObject.getString("link")),
//        share =         URL(jsonObject.getString("share")),
//        duration =      jsonObject.getInt("duration"),
//        release_date =  jsonObject.getString("release_date"), //TODO: to DateTime
        cover =   URL(    jsonObject.getString("preview")), //TODO: to Picture/Bitmap?
//        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
//        artist =        jsonObject.getJSONObject("artist"), //TODO: to Artist
    )

    override fun toString(): String {
        return "Track(id='$id', title='$title')"
    }
}