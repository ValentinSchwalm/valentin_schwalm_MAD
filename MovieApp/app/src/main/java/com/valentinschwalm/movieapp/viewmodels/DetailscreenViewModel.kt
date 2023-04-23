package com.valentinschwalm.movieapp.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinschwalm.movieapp.models.Movie
import com.valentinschwalm.movieapp.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DetailscreenViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll().collect {movies ->
                if (!movies.isNullOrEmpty()){
                    _movieList.value = movies
                }
            }
        }
    }

    suspend fun toggleFavorite(movieID: String) {
        val movie = repository.getByID(movieID)
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }

    fun getMovieByID(movieID: String): Movie {
        return  _movieList.value.first{
            it.id == movieID
        }
    }
}