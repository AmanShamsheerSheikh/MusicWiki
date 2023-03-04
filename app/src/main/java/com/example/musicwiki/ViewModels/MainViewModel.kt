package com.example.musicwiki.ViewModels

import androidx.lifecycle.*
import com.example.musicwiki.Repositorys.tagRepository
import com.example.musicwiki.data.tag.Tag
import kotlinx.coroutines.*

class MainViewModel(private val repository: tagRepository): ViewModel() {


    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getTags()
        }

    }

    val tags : LiveData<List<Tag>>
        get() = repository.tags


}