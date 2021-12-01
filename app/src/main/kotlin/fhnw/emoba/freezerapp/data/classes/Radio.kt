package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.lang.Exception
import java.net.URL

data class Radio(
    val id: Int,
    val title: String,
    val picture: URL,

    val share: URL,

//    val tracklist: List<Track>
) {
    constructor(jsonObject: JSONObject) : this(
        id =                jsonObject.getInt("id"),
        title =             jsonObject.getString("title").trim(),
        picture =           URL(jsonObject.getString("picture")), //TODO: to Picture/Bitmap?

        share =             try {URL(jsonObject.getString("share"))}    catch (e: Exception) {URL("https://google.com/doesntexist")},

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