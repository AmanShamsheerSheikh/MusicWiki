package com.example.musicwiki.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.data.tagInfo.TagInfo
import com.example.musicwiki.data.topAlbumData.topAlbums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumViewModel(private val repository: InfoRepository,private val name: String) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getTopAlbums(name)
        }

    }

    val topAlbums : LiveData<topAlbums>
        get() = repository.topAlbums
}