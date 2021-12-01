package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.net.URL

data class Radio(
    val id: Int,
    val title: String,
    val share: URL,
    val picture: URL,
//    val tracklist: List<Track>
) {
    constructor(jsonObject: JSONObject) : this(
        id =                jsonObject.getInt("id"),
        title =             jsonObject.getString("title"),
        picture =           URL(jsonObject.getString("picture")), //TODO: to Picture/Bitmap?
        share =             URL(jsonObject.getString("share")),
//        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
    )

    constructor() : this(
        id =            0,
        title =         "",
        picture =       URL("https://google.com/doesntexist"), //TODO: to Picture/Bitmap?, no_image handling
        share =         URL("https://google.com/doesntexist"),
//        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
    )

    override fun toString(): String {
        return "Track(id='$id', title='$title')"
    }
}