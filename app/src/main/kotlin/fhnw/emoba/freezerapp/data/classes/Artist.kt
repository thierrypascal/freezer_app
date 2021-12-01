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
        name =          jsonObject.getString("name").trim(),
        link =          URL(jsonObject.getString("link")),
        picture =       URL(jsonObject.getString("picture")), //TODO: to Picture/Bitmap?
//        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
        )

    constructor() : this(
        id =            0,
        name =          "",
        link =          URL("https://google.com/doesntexist"),
        picture =       URL("https://google.com/doesntexist"), //TODO: to Picture/Bitmap?, no_image handling
//        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
    )


    override fun toString(): String {
            return "Track(id='$id', title='$name', link='$link')"
        }
}