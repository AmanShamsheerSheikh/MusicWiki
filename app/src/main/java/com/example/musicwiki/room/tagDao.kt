package com.example.musicwiki.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicwiki.data.tag.Tag



@Dao
interface tagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTags(tags:List<Tag>)

    @Query("SELECT * FROM tags")
    fun getTag() : List<Tag>

}