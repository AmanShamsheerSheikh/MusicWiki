package com.example.musicwiki.data.albumData

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")val text: String,
    val size: String
)