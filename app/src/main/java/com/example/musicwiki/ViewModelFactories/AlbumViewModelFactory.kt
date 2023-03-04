package com.example.musicwiki.ViewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModels.DetailViewModel
import com.example.musicwiki.fragments.AlbumViewModel

class AlbumViewModelFactory(private val repository: InfoRepository,private val name : String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(repository,name) as T
    }
}