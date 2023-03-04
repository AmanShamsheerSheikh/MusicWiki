package com.example.musicwiki.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.data.albumData.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumActivityViewModel(private val repository: InfoRepository,private val album: String,private val artist: String) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getAlbum(artist,album)
        }

    }

    val Album : LiveData<Album>
        get() = repository.album
}