package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.net.URL
import java.util.*

class Track(
    val id: Int,
//    val readable: Boolean,
    val title: String,
//    val title_short: String
//    val unseen: Boolean,
//    val link: URL,
//    val track_position: Int,
//    val disk_number: Int,
//    val rank: Int,
//    val release_date: Date,
//    val explicit_lyrics: Boolean,
//    val preview: URL,
//    val contributors: String,
//    val artist: Artist,
//    val album: Album,

) {  //diesen Konstruktor gibt es hauptsaechlich fuer TestZwecke, z.B. im Preview

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getInt("id"),
//        jsonObject.getBoolean("readable"),
        jsonObject.getString("title"),
//        jsonObject.getString("title_short"),
//        jsonObject.getBoolean("unseen"),
//        jsonObject.getString("link"),//TODO: to URL
//        jsonObject.getInt("track_position"),
//        jsonObject.getInt("disk_number"),
//        jsonObject.getInt("rank"),
    )

    override fun toString(): String {
        return "Track(id='$id', title='$title')"
    }
}