package com.example.musicwiki.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicwiki.data.tag.Tag

@Database(entities = [Tag::class], version = 1)
abstract class tagDatabase : RoomDatabase(){
    abstract fun tagDao() : tagDao
    companion object{
        @Volatile
        private var instance : tagDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                tagDatabase::class.java,
                "tags.db"
            ).createFromAsset("database/tags_db-tags.sql")
                .build()


    }


}