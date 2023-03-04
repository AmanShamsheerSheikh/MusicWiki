package com.example.musicwiki.ViewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModels.ArtistActivityViewModel
import com.example.musicwiki.fragments.ArtistViewModel

class ArtistActivityViewModelfactory(private val repository: InfoRepository, private val name:String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistActivityViewModel(repository,name) as T
    }
}