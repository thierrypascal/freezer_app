package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.net.URL

data class Album(
    val id: Int,
    val title: String,
    val cover: URL,
//    val tracklist: List<Track>

//    val link: URL,
//    val share: URL,
//    val duration: Int,
//    val release_date: String,
//    val artist: Artist,
) {
    constructor(jsonObject: JSONObject) : this(
        id =            jsonObject.getInt("id"),
        title =         jsonObject.getString("title"),
        cover =         URL(jsonObject.getString("cover")), //TODO: to Picture/Bitmap?
//        link =          URL(jsonObject.getString("link")),
//        share =         URL(jsonObject.getString("share")),
//        duration =      jsonObject.getInt("duration"),
//        release_date =  jsonObject.getString("release_date"), //TODO: to DateTime
////        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
//        artist =      Artist(jsonObject.getJSONObject("artist")),
    )

    constructor() : this(
        id =            0,
        title =         "",
        cover =         URL("https://google.com/doesntexist"), //TODO: to Picture/Bitmap?, no_image handling
//        link =          URL("https://google.com/doesntexist"),
//        share =         URL("https://google.com/doesntexist"),
//        duration =      0,
//        release_date =  "", //TODO: to DateTime
////        tracklist =     loadTracklistByUrl(URL(jsonObject.getString("tracklist"))) //TODO: load tracklist by url
//        artist =      Artist(),
    )

    override fun toString(): String {
        return "Track(id='$id', title='$title')"
    }
}