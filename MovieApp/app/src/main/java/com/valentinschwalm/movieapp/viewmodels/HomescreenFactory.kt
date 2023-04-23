package com.valentinschwalm.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.valentinschwalm.movieapp.repositories.MovieRepository

class HomescreenFactory(private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomescreenViewModel::class.java)) {
            return HomescreenViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class!")
    }
}