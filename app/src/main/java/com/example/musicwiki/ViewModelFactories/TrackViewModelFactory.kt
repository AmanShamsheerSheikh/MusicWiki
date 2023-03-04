package com.example.musicwiki.ViewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModels.MainViewModel
import com.example.musicwiki.fragments.ArtistViewModel
import com.example.musicwiki.fragments.TrackViewModel

class TrackViewModelFactory(private val repository: InfoRepository,private val name:String):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrackViewModel(repository,name) as T
    }
}