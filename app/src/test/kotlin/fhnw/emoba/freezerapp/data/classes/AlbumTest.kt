package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.URL

internal class AlbumTest {
    private lateinit var jsonStringFull: String
    private lateinit var jsonStringStriped: String

    @Before
    fun setUp() {
        jsonStringFull = """
            {
              "id": "302127",
              "title": "Discovery",
              "upc": "724384960650",
              "link": "https://www.deezer.com/album/302127",
              "share": "https://www.deezer.com/album/302127?utm_source=deezer&utm_content=album-302127&utm_term=0_1638489792&utm_medium=web",
              "cover": "https://api.deezer.com/album/302127/image",
              "cover_small": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/56x56-000000-80-0-0.jpg",
              "cover_medium": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/250x250-000000-80-0-0.jpg",
              "cover_big": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/500x500-000000-80-0-0.jpg",
              "cover_xl": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/1000x1000-000000-80-0-0.jpg",
              "md5_image": "2e018122cb56986277102d2041a592c8",
              "genre_id": 113,
              "label": "Daft Life Ltd./ADA France",
              "nb_tracks": 14,
              "duration": 3660,
              "fans": 248124,
              "release_date": "2001-03-07",
              "record_type": "album",
              "available": true,
              "tracklist": "https://api.deezer.com/album/302127/tracks",
              "explicit_lyrics": false,
              "explicit_content_lyrics": 7,
              "explicit_content_cover": 0,
              "artist": {
                "id": "27",
                "name": "Daft Punk",
                "picture": "https://api.deezer.com/artist/27/image",
                "picture_small": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/56x56-000000-80-0-0.jpg",
                "picture_medium": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/250x250-000000-80-0-0.jpg",
                "picture_big": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/500x500-000000-80-0-0.jpg",
                "picture_xl": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/1000x1000-000000-80-0-0.jpg",
                "tracklist": "https://api.deezer.com/artist/27/top?limit=50",
                "type": "artist"
              },
              "type": "album",
            }
        """

        jsonStringStriped = """
            {
              "id": "302127",
              "title": "Discovery",
              "cover": "https://api.deezer.com/album/302127/image",
              "tracklist": "https://api.deezer.com/album/302127/tracks",
            }
        """
    }

    @Test
    fun testConstructor(){
        //given
        val jsonFull = JSONObject(jsonStringFull)
        val jsonStriped = JSONObject(jsonStringStriped)

        //when
        val a1 = Album(jsonFull)
        val a2 = Album(jsonStriped)

        //then
        with(a1){
            assertEquals(302127, id)
            assertEquals("Discovery", title)
            assertEquals("https://api.deezer.com/album/302127/image", cover)
            assertEquals("Daft Life Ltd./ADA France", label)
            assertEquals(14, nb_tracks)
            assertEquals(URL("https://www.deezer.com/album/302127"), link)
            assertEquals(URL("https://www.deezer.com/album/302127?utm_source=deezer&utm_content=album-302127&utm_term=0_1638489792&utm_medium=web"), share)
            assertEquals(3660, duration)
            assertEquals(248124, fans)
            assertEquals("2001-03-07", release_date)
            assertEquals("https://api.deezer.com/album/302127/tracks", tracklist)
            //artist not tested, as it uses a constructor tested in a separate class
        }

        with(a2){
            assertEquals(302127, id)
            assertEquals("Discovery", title)
            assertEquals("https://api.deezer.com/album/302127/image", cover)
            assertEquals("", label)
            assertEquals(0, nb_tracks)
            assertEquals(URL("https://google.com/doesntexist"), link)
            assertEquals(URL("https://google.com/doesntexist"), share)
            assertEquals(0, duration)
            assertEquals(0, fans)
            assertEquals("NaN", release_date)
            assertEquals("https://api.deezer.com/album/302127/tracks", tracklist)
            //artist not tested, as it uses a constructor tested in a separate class
        }
    }

    @Test
    fun testEmptyConstructor(){
        //when
        val a3 = Album()

        //then
        with(a3){
            assertEquals(0, id)
            assertEquals("", title)
            assertEquals("https://google.com/doesntexist", cover)
            assertEquals("", label)
            assertEquals(0, nb_tracks)
            assertEquals(URL("https://google.com/doesntexist"), link)
            assertEquals(URL("https://google.com/doesntexist"), share)
            assertEquals(0, duration)
            assertEquals(0, fans)
            assertEquals("NaN", release_date)
            assertEquals("", tracklist)
            //artist not tested, as it uses a constructor tested in a separate class
        }
    }

    @Test
    fun testPrimaryConstructor(){
        //when
        val a4 = Album(550, "", "https://google.com/doesntexist", "label", 0, URL("https://google.com/doesntexist"), URL("https://google.com/doesntexist"), 0, 0, "NaN", Artist(), "")

        //then
        with(a4){
            assertEquals(550, id)
            assertEquals("", title)
            assertEquals("https://google.com/doesntexist", cover)
            assertEquals("label", label)
            assertEquals(0, nb_tracks)
            assertEquals(URL("https://google.com/doesntexist"), link)
            assertEquals(URL("https://google.com/doesntexist"), share)
            assertEquals(0, duration)
            assertEquals(0, fans)
            assertEquals("NaN", release_date)
            assertEquals("", tracklist)
            //artist not tested, as it uses a constructor tested in a separate class
        }
    }

    @Test
    fun testToString(){
        //given
        val jsonFull = JSONObject(jsonStringFull)

        //when
        val a5 = Album(jsonFull)

        //then
        assertEquals("Album(id='302127', title='Discovery')", a5.toString())
    }
}