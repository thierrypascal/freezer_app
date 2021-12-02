package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.net.URL

data class Artist (
    val id: Int,
    val name: String,
    val link: URL,
    val picture: String,

    val nb_album: Int,
    val nb_fan: Int,

    val tracklist: String
) {
    constructor(jsonObject: JSONObject) : this(
        id =            jsonObject.getInt("id"),
        name =          jsonObject.getString("name").trim(),
        link =          URL(jsonObject.getString("link")),
        picture =       jsonObject.getString("picture"),

        nb_album =      jsonObject.getInt("nb_album"),
        nb_fan =        jsonObject.getInt("nb_fan"),

        tracklist =     jsonObject.getString("tracklist"),
    )

    constructor() : this(
        id =            0,
        name =          "",
        link =          URL("https://google.com/doesntexist"),
        picture =       "https://google.com/doesntexist",

        nb_album =      0,
        nb_fan =        0,

        tracklist =     "",
    )


    override fun toString(): String {
            return "Track(id='$id', title='$name', link='$link')"
        }
}