package com.example.musicwiki.ViewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModels.DetailViewModel
import com.example.musicwiki.ViewModels.MainViewModel

class DetailViewModelFactory(val repository: InfoRepository,private val name:String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository,name) as T
    }
}