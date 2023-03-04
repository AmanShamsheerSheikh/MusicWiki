package com.example.musicwiki.Repositorys

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.data.tag.Tag
import com.example.musicwiki.room.tagDatabase
import com.example.musicwiki.util.MyUtils

class tagRepository(private val api:DetailsApi,private val tagDatabase: tagDatabase,private val context: Context) {


    private val _tags = MutableLiveData<List<Tag>>()
    val tags : LiveData<List<Tag>>
    get() = _tags

    suspend fun getTags(){
        if(MyUtils.isInternetAvailable(context)){
            val result = api.getTags()
            if(result.body() != null){

                tagDatabase.tagDao().insertTags(result.body()!!.tags.tag)
                _tags.postValue(result.body()!!.tags.tag)
            }
        }else{
            val tags = tagDatabase.tagDao().getTag()

            _tags.postValue(tags)
        }
    }

}