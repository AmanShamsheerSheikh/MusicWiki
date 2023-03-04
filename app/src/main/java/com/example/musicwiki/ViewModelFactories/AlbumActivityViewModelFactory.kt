package com.example.musicwiki.ViewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModels.AlbumActivityViewModel
import com.example.musicwiki.fragments.AlbumViewModel

class AlbumActivityViewModelFactory(private val repository: InfoRepository,private val artist: String,private val album: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumActivityViewModel(repository,album,artist) as T
    }
}