package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.net.URL
import java.util.*

data class Track(
    val id: Int,
    val title: String,
    val title_short: String,
    val link: URL,
//    val share: URL,
    val duration: Int,
//    val track_position: Int,
//    val release_date: String,
    val explicit_lyrics: Boolean,
    val preview: URL,
//    val artist: Artist,
//    val album: Album,

    ) {
    constructor(jsonObject: JSONObject) : this(
        id =            jsonObject.getInt("id"),
        title =         jsonObject.getString("title"),
        title_short =   jsonObject.getString("title_short"),
        link =          URL(jsonObject.getString("link")),
//        share =         URL(jsonObject.getString("share")),
        duration =      jsonObject.getInt("duration"),
//        track_position = jsonObject.getInt("track_position"),
//        release_date =  jsonObject.getString("release_date"), //TODO: to DateTime
        explicit_lyrics = jsonObject.getBoolean("explicit_lyrics"),
        preview =       URL(jsonObject.getString("preview")),
//        artist =        jsonObject.getJSONObject("artist"), //TODO: to Artist
//        album =         jsonObject.getJSONObject("album"), //TODO: to Album
    )

    override fun toString(): String {
        return "Track(id='$id', title='$title', link='$link')"
    }
}