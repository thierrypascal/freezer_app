package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import java.lang.Exception
import java.net.URL

data class Artist (
    val id: Int,
    val name: String,

    val picture: String,
    val link: URL,
    val nb_album: Int,
    val nb_fan: Int,

    val tracklist: String
) {
    constructor(jsonObject: JSONObject) : this(
        id =            jsonObject.getInt("id"),
        name =          jsonObject.getString("name").trim(),

        picture =       try {jsonObject.getString("picture")}   catch (e: Exception) {""},
        link =          try {URL(jsonObject.getString("link"))} catch (e: Exception) {URL("https://google.com/doesntexist")},
        nb_album =      try {jsonObject.getInt("nb_album")}     catch (e: Exception) {0},
        nb_fan =        try {jsonObject.getInt("nb_fan")}       catch (e: Exception) {0},

        tracklist =     jsonObject.getString("tracklist"),
    )

    constructor() : this(
        id =            0,
        name =          "",

        link =          URL("https://google.com/doesntexist"),
        picture =       "",
        nb_album =      0,
        nb_fan =        0,

        tracklist =     "",
    )


    override fun toString(): String {
            return "Artist(id='$id', title='$name', link='$link')"
        }
}