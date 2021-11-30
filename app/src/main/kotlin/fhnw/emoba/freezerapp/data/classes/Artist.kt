package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.net.URL

data class Artist (
    val id: Int,
    val name: String,
    val link: URL,
    val picture: URL,
//    val tracklist: List<Track>
    ) {
        constructor(jsonObject: JSONObject) : this(
        id =            jsonObject.getInt("id"),
        name =          jsonObject.getString("title"),
        link =          URL(jsonObject.getString("link")),
        picture =       URL(jsonObject.getString("preview")), //TODO: to Picture/Bitmap?
//        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
        )

        override fun toString(): String {
            return "Track(id='$id', title='$name', link='$link')"
        }
}