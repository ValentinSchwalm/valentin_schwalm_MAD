package com.valentinschwalm.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.valentinschwalm.movieapp.repositories.MovieRepository

class FavoritescreenFactory(private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritescreenViewModel::class.java)) {
            return FavoritescreenViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class!")
    }
}