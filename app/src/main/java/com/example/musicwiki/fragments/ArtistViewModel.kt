package com.example.musicwiki.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.data.topAlbumData.topAlbums
import com.example.musicwiki.data.topArtistsData.TopArtists
import com.example.musicwiki.data.trackData.TrackData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistViewModel(private val repository: InfoRepository,private val name: String) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getTopArtists(name)
            repository.getTopTracks(name)
            repository.getTopAlbums(name)
        }

    }

    val topArtists : LiveData<TopArtists>
        get() = repository.topArtists

    val tracks : LiveData<TrackData>
        get() = repository.tracks

    val topAlbums : LiveData<topAlbums>
        get() = repository.topAlbums

}