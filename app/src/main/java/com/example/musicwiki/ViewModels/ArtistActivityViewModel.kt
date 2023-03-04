package com.example.musicwiki.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.data.albumData.Album
import com.example.musicwiki.data.artistData.Artist
import com.example.musicwiki.data.artistTopAlbums.ArtistAlbums
import com.example.musicwiki.data.artistTopTracks.ArtistTracks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistActivityViewModel(private val repository: InfoRepository,private val artistName: String) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.Main){
            repository.getArtist(artistName)
            repository.getArtistTopAlbums(artistName)
            repository.getArtistTopTracks(artistName)
        }
    }

    val Artist : LiveData<Artist>
        get() = repository.artist
    val ArtistTopAlbums : LiveData<ArtistAlbums>
        get() = repository.artistTopAlbums
    val ArtistTopTracks : LiveData<ArtistTracks>
        get() = repository.artistTopTracks

}