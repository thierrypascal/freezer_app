package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.net.URL

data class Radio (
    val id: Int,
    val title: String,
//    val share: URL,
    val picture: URL,
//    val tracklist: List<Track>
) {
    constructor(jsonObject: JSONObject) : this(
        id =            jsonObject.getInt("id"),
        title =          jsonObject.getString("title"),
//        share =         URL(jsonObject.getString("share")),
        picture =       URL(jsonObject.getString("preview")), //TODO: to Picture/Bitmap?
//        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
    )

    override fun toString(): String {
        return "Track(id='$id', title='$title')"
    }
}