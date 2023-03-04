package com.example.musicwiki.data.tag

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey
    var name: String,
    val reach: String,
    val streamable: String,
    val taggings: String,
    val url: String,
)