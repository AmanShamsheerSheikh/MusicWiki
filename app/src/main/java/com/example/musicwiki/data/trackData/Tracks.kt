package com.example.musicwiki.data.trackData

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("@attr")val attr: Attr,
    val track: List<Track>
)