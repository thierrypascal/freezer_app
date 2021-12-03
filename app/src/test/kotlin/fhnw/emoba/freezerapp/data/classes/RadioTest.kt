package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.URL

internal class RadioTest {
    private lateinit var jsonStringFull: String
    private lateinit var jsonStringStriped: String

    @Before
    fun setUp() {
        jsonStringFull = """
            {
              "id": "39051",
              "title": "Sad Songs",
              "description": "Sad Songs",
              "share": "https://www.deezer.com/mixes/genre/39051?utm_source=deezer&utm_content=mixes-genre-39051&utm_term=0_1638489193&utm_medium=web",
              "picture": "https://api.deezer.com/radio/39051/image",
              "picture_small": "https://e-cdns-images.dzcdn.net/images/misc/ad6deec279499b2fd277de977df088fd/56x56-000000-80-0-0.jpg",
              "picture_medium": "https://e-cdns-images.dzcdn.net/images/misc/ad6deec279499b2fd277de977df088fd/250x250-000000-80-0-0.jpg",
              "picture_big": "https://e-cdns-images.dzcdn.net/images/misc/ad6deec279499b2fd277de977df088fd/500x500-000000-80-0-0.jpg",
              "picture_xl": "https://e-cdns-images.dzcdn.net/images/misc/ad6deec279499b2fd277de977df088fd/1000x1000-000000-80-0-0.jpg",
              "tracklist": "https://api.deezer.com/radio/39051/tracks",
              "md5_image": "ad6deec279499b2fd277de977df088fd",
              "type": "radio"
            }
        """

        jsonStringStriped = """
            {
              "id": "39051",
              "title": "Sad Songs",
              "picture": "https://api.deezer.com/radio/39051/image",
              "tracklist": "https://api.deezer.com/radio/39051/tracks",
            }
        """
    }

    @Test
    fun testConstructor(){
        //given
        val jsonFull = JSONObject(jsonStringFull)
        val jsonStriped = JSONObject(jsonStringStriped)

        //when
        val r1 = Radio(jsonFull)
        val r2 = Radio(jsonStriped)

        //then
        with(r1){
            assertEquals(39051, id)
            assertEquals("Sad Songs", title)
            assertEquals("https://api.deezer.com/radio/39051/image", picture)
            assertEquals(URL("https://www.deezer.com/mixes/genre/39051?utm_source=deezer&utm_content=mixes-genre-39051&utm_term=0_1638489193&utm_medium=web"), share)
            assertEquals("https://api.deezer.com/radio/39051/tracks", tracklist)
        }

        with(r2){
            assertEquals(39051, id)
            assertEquals("Sad Songs", title)
            assertEquals("https://api.deezer.com/radio/39051/image", picture)
            assertEquals(URL("https://google.com/doesntexist"), share)
            assertEquals("https://api.deezer.com/radio/39051/tracks", tracklist)
        }
    }

    @Test
    fun testEmptyConstructor(){
        //when
        val r3 = Radio()

        //then
        with(r3){
            assertEquals(0, id)
            assertEquals("", title)
            assertEquals("https://google.com/doesntexist", picture)
            assertEquals(URL("https://google.com/doesntexist"), share)
            assertEquals("", tracklist)
        }
    }

    @Test
    fun testPrimaryConstructor(){
        //when
        val r4 = Radio(250, "JUNIT MACHT SPASS", "https://google.com/doesntexist", URL("https://google.com/doesntexist"), "")

        //then
        with(r4){
            assertEquals(250, id)
            assertEquals("JUNIT MACHT SPASS", title)
            assertEquals("https://google.com/doesntexist", picture)
            assertEquals(URL("https://google.com/doesntexist"), share)
            assertEquals("", tracklist)
        }
    }

    @Test
    fun testToString(){
        //given
        val jsonFull = JSONObject(jsonStringFull)

        //when
        val r5 = Radio(jsonFull)

        //then
        assertEquals("Radio(id='39051', title='Sad Songs')", r5.toString())
    }
}