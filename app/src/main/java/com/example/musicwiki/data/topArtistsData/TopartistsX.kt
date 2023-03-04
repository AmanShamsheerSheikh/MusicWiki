package com.example.musicwiki.data.topArtistsData

import com.google.gson.annotations.SerializedName

data class TopartistsX(
    @SerializedName("attr")val attr: Attr,
    val artist: List<Artist>
)