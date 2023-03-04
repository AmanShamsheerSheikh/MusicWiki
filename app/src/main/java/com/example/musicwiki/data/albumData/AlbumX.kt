package com.example.musicwiki.data.albumData

data class AlbumX(
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val tags: Tags,
    val tracks: Tracks,
    val url: String,
    val wiki: Wiki? = Wiki("DATA NOT FOUND","","DATA NOT FOUND")
)