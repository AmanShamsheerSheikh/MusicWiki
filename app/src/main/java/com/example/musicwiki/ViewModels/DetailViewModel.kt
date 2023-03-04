package com.example.musicwiki.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.data.tagInfo.TagInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: InfoRepository,private val name : String) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getInfo(name)
        }

    }

    val tagsInfo : LiveData<TagInfo>
        get() = repository.tagInfo
}