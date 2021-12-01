package fhnw.emoba.freezerapp.model

enum class Tabs(val title: String, val type: Int) {
    //Int type corresponds to the type of list that should be used in SearchScreen
    TRACK("Song", 0),
    ARTIST("Künstler", 1),
    ALBUM("Alben", 2),
    RADIO("Radio", 3),
}