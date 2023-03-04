package com.example.musicwiki.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.data.topArtistsData.TopArtists
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistViewModel(private val repository: InfoRepository,private val name: String) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getTopArtists(name)
        }

    }

    val topAlbums : LiveData<TopArtists>
        get() = repository.topArtists
}