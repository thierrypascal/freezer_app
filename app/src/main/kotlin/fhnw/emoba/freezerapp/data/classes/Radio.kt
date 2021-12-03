package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.lang.Exception
import java.net.URL

data class Radio(
    val id: Int,
    val title: String,
    val picture: String,

    val share: URL,

    val tracklist: String
) {
    constructor(jsonObject: JSONObject) : this(
        id =                jsonObject.getInt("id"),
        title =             jsonObject.getString("title").trim(),
        picture =           jsonObject.getString("picture"),

        share =             try {URL(jsonObject.getString("share"))}    catch (e: Exception) {URL("https://google.com/doesntexist")},

        tracklist =         jsonObject.getString("tracklist"),
    )

    constructor() : this(
        id =            0,
        title =         "",
        picture =       "https://google.com/doesntexist",

        share =         URL("https://google.com/doesntexist"),

        tracklist =     "",
    )

    override fun toString(): String {
        return "Radio(id='$id', title='$title')"
    }
}