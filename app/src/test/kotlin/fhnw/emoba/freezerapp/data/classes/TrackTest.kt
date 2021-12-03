package fhnw.emoba.freezerapp.data.classes

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.URL

internal class TrackTest {
    private lateinit var jsonStringFull: String
    private lateinit var jsonStringStriped: String

    @Before
    fun setUp() {
        jsonStringFull = """
             {"id": "3135556",
              "readable": true,
              "title": "Harder, Better, Faster, Stronger",
              "title_short": "Harder, Better, Faster, Stronger",
              "title_version": "",
              "isrc": "GBDUW0000059",
              "link": "https://www.deezer.com/track/3135556",
              "share": "https://www.deezer.com/track/3135556?utm_source=deezer&utm_content=track-3135556&utm_term=0_1638486823&utm_medium=web",
              "duration": "224",
              "track_position": 4,
              "disk_number": 1,
              "rank": "784948",
              "release_date": "2005-01-24",
              "explicit_lyrics": false,
              "explicit_content_lyrics": 0,
              "explicit_content_cover": 0,
              "preview": "https://cdns-preview-d.dzcdn.net/stream/c-deda7fa9316d9e9e880d2c6207e92260-8.mp3",
              "bpm": 123.4,
              "gain": -12.4,
              "md5_image": "2e018122cb56986277102d2041a592c8",
              "artist": {
                "id": "27",
                "name": "Daft Punk",
                "link": "https://www.deezer.com/artist/27",
                "share": "https://www.deezer.com/artist/27?utm_source=deezer&utm_content=artist-27&utm_term=0_1638486823&utm_medium=web",
                "picture": "https://api.deezer.com/artist/27/image",
                "picture_small": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/56x56-000000-80-0-0.jpg",
                "picture_medium": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/250x250-000000-80-0-0.jpg",
                "picture_big": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/500x500-000000-80-0-0.jpg",
                "picture_xl": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/1000x1000-000000-80-0-0.jpg",
                "radio": true,
                "tracklist": "https://api.deezer.com/artist/27/top?limit=50",
                "type": "artist"
              },
              "album": {
                "id": "302127",
                "title": "Discovery",
                "link": "https://www.deezer.com/album/302127",
                "cover": "https://api.deezer.com/album/302127/image",
                "cover_small": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/56x56-000000-80-0-0.jpg",
                "cover_medium": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/250x250-000000-80-0-0.jpg",
                "cover_big": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/500x500-000000-80-0-0.jpg",
                "cover_xl": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/1000x1000-000000-80-0-0.jpg",
                "md5_image": "2e018122cb56986277102d2041a592c8",
                "release_date": "2001-03-07",
                "tracklist": "https://api.deezer.com/album/302127/tracks",
                "type": "album"
              },
              "type": "track"
            }
        """

        jsonStringStriped = """
             {"id": "3135556",
              "title": "Harder, Better, Faster, Stronger",
              "title_short": "Harder, Better, Faster, Stronger",
              "duration": "224",
              "explicit_lyrics": false,
              "preview": "https://cdns-preview-d.dzcdn.net/stream/c-deda7fa9316d9e9e880d2c6207e92260-8.mp3",
              "artist": {
                "id": "27",
                "name": "Daft Punk",
                "link": "https://www.deezer.com/artist/27",
                "share": "https://www.deezer.com/artist/27?utm_source=deezer&utm_content=artist-27&utm_term=0_1638486823&utm_medium=web",
                "picture": "https://api.deezer.com/artist/27/image",
                "picture_small": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/56x56-000000-80-0-0.jpg",
                "picture_medium": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/250x250-000000-80-0-0.jpg",
                "picture_big": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/500x500-000000-80-0-0.jpg",
                "picture_xl": "https://e-cdns-images.dzcdn.net/images/artist/f2bc007e9133c946ac3c3907ddc5d2ea/1000x1000-000000-80-0-0.jpg",
                "radio": true,
                "tracklist": "https://api.deezer.com/artist/27/top?limit=50",
                "type": "artist"
              },
              "album": {
                "id": "302127",
                "title": "Discovery",
                "link": "https://www.deezer.com/album/302127",
                "cover": "https://api.deezer.com/album/302127/image",
                "cover_small": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/56x56-000000-80-0-0.jpg",
                "cover_medium": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/250x250-000000-80-0-0.jpg",
                "cover_big": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/500x500-000000-80-0-0.jpg",
                "cover_xl": "https://e-cdns-images.dzcdn.net/images/cover/2e018122cb56986277102d2041a592c8/1000x1000-000000-80-0-0.jpg",
                "md5_image": "2e018122cb56986277102d2041a592c8",
                "release_date": "2001-03-07",
                "tracklist": "https://api.deezer.com/album/302127/tracks",
                "type": "album"
              },
            }
        """
    }

    @Test
    fun testConstructor(){
        //given
        val jsonFull = JSONObject(jsonStringFull)
        val jsonStriped = JSONObject(jsonStringStriped)

        //when
        val t1 = Track(jsonFull)
        val t2 = Track(jsonStriped)

        //then
        with(t1){
            assertEquals(3135556, id)
            assertEquals("Harder, Better, Faster, Stronger", title)
            assertEquals("Harder, Better, Faster, Stronger", title_short)
            assertEquals(224, duration)
            assertEquals(false, explicit_lyrics)
            assertEquals("https://cdns-preview-d.dzcdn.net/stream/c-deda7fa9316d9e9e880d2c6207e92260-8.mp3", preview)
            assertEquals(URL("https://www.deezer.com/track/3135556"), link)
            assertEquals(URL("https://www.deezer.com/track/3135556?utm_source=deezer&utm_content=track-3135556&utm_term=0_1638486823&utm_medium=web"), share)
            assertEquals(4, track_position)
            assertEquals("2005-01-24", release_date)
            //album and artist not tested, as the use a constructor tested in separate class
        }

        with(t2){
            assertEquals(3135556, id)
            assertEquals("Harder, Better, Faster, Stronger", title)
            assertEquals("Harder, Better, Faster, Stronger", title_short)
            assertEquals(224, duration)
            assertEquals(false, explicit_lyrics)
            assertEquals("https://cdns-preview-d.dzcdn.net/stream/c-deda7fa9316d9e9e880d2c6207e92260-8.mp3", preview)
            assertEquals(URL("https://google.com/doesntexist"), link)
            assertEquals(URL("https://google.com/doesntexist"), share)
            assertEquals(0, track_position)
            assertEquals("NaN", release_date)
            //album and artist not tested, as the use a constructor tested in separate class
        }
    }

    @Test
    fun testEmptyConstructor(){
        //when
        val t3 = Track()

        //then
        with(t3){
            assertEquals(0, id)
            assertEquals("", title)
            assertEquals("", title_short)
            assertEquals(0, duration)
            assertEquals(true, explicit_lyrics)
            assertEquals("https://google.com/doesntexist", preview)
            assertEquals(URL("https://google.com/doesntexist"), link)
            assertEquals(URL("https://google.com/doesntexist"), share)
            assertEquals(0, track_position)
            assertEquals("NaN", release_date)
            //album and artist not tested, as the use a constructor tested in separate class
        }
    }

    @Test
    fun testPrimaryConstructor(){
        //when
        val t4 = Track(0, "", "", URL("https://google.com/doesntexist"), 0, true, "https://google.com/doesntexist", Artist(), Album(), URL("https://google.com/doesntexist"), 0, "NaN")

        //then
        with(t4){
            assertEquals(0, id)
            assertEquals("", title)
            assertEquals("", title_short)
            assertEquals(0, duration)
            assertEquals(true, explicit_lyrics)
            assertEquals("https://google.com/doesntexist", preview)
            assertEquals(URL("https://google.com/doesntexist"), link)
            assertEquals(URL("https://google.com/doesntexist"), share)
            assertEquals(0, track_position)
            assertEquals("NaN", release_date)
            //album and artist not tested, as the use a constructor tested in separate class
        }
    }

    @Test
    fun testToString(){
        //given
        val jsonFull = JSONObject(jsonStringFull)

        //when
        val t5 = Track(jsonFull)

        //then
        assertEquals("Track(id='3135556', title='Harder, Better, Faster, Stronger', link='https://www.deezer.com/track/3135556')", t5.toString())
    }
}