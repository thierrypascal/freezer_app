package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.URL

internal class ArtistTest {
    private lateinit var jsonStringFull: String
    private lateinit var jsonStringStriped: String

    @Before
    fun setUp() {
        jsonStringFull = """
            {
              "id": "27",
              "name": "Daft Punk",
              "link": "https://www.deezer.com/artist/27",
              "share": "https://www.deezer.com/artist/27?utm_source=deezer&utm_content=artist-27&utm_term=0_1638488473&utm_medium=web",
              "picture": "https://api.deezer.com/artist/27/image",
              "picture_small": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/56x56-000000-80-0-0.jpg",
              "picture_medium": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/250x250-000000-80-0-0.jpg",
              "picture_big": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/500x500-000000-80-0-0.jpg",
              "picture_xl": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/1000x1000-000000-80-0-0.jpg",
              "nb_album": 31,
              "nb_fan": 4145825,
              "radio": true,
              "tracklist": "https://api.deezer.com/artist/27/top?limit=50",
              "type": "artist"
            }
        """

        jsonStringStriped = """
            {
              "id": "27",
              "name": "Daft Punk",
              "tracklist": "https://api.deezer.com/artist/27/top?limit=50",
            }
        """
    }

    @Test
    fun testConstructor(){
        //given
        val jsonFull = JSONObject(jsonStringFull)
        val jsonStriped = JSONObject(jsonStringStriped)

        //when
        val a1 = Artist(jsonFull)
        val a2 = Artist(jsonStriped)

        //then
        with(a1){
            assertEquals(27, id)
            assertEquals("Daft Punk", name)
            assertEquals("https://api.deezer.com/artist/27/image", picture)
            assertEquals(URL("https://www.deezer.com/artist/27"), link)
            assertEquals(31, nb_album)
            assertEquals(4145825, nb_fan)
            assertEquals("https://api.deezer.com/artist/27/top?limit=50", tracklist)
        }

        with(a2){
            assertEquals(27, id)
            assertEquals("Daft Punk", name)
            assertEquals("", picture)
            assertEquals(URL("https://google.com/doesntexist"), link)
            assertEquals(0, nb_album)
            assertEquals(0, nb_fan)
            assertEquals("https://api.deezer.com/artist/27/top?limit=50", tracklist)
        }
    }

    @Test
    fun testEmptyConstructor(){
        //when
        val a3 = Artist()

        //then
        with(a3){
            assertEquals(0, id)
            assertEquals("", name)
            assertEquals("", picture)
            assertEquals(URL("https://google.com/doesntexist"), link)
            assertEquals(0, nb_album)
            assertEquals(0, nb_fan)
            assertEquals("", tracklist)
        }
    }

    @Test
    fun testPrimaryConstructor(){
        //when
        val a4 = Artist(100, "", "", URL("https://google.com/doesntexist"), 0, 0, "")

        //then
        with(a4){
            assertEquals(100, id)
            assertEquals("", name)
            assertEquals("", picture)
            assertEquals(URL("https://google.com/doesntexist"), link)
            assertEquals(0, nb_album)
            assertEquals(0, nb_fan)
            assertEquals("", tracklist)
        }
    }

    @Test
    fun testToString(){
        //given
        val jsonFull = JSONObject(jsonStringFull)

        //when
        val a5 = Artist(jsonFull)

        //then
        assertEquals("Artist(id='27', title='Daft Punk', link='https://www.deezer.com/artist/27')", a5.toString())
    }
}