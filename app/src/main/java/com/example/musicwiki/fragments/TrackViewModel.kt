package com.example.musicwiki.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.data.topArtistsData.TopArtists
import com.example.musicwiki.data.trackData.TrackData
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrackViewModel(private val repository: InfoRepository,private val name: String) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getTopTracks(name)
        }

    }

    val tracks : LiveData<TrackData>
        get() = repository.tracks
}