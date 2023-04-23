package com.valentinschwalm.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinschwalm.movieapp.models.Movie
import com.valentinschwalm.movieapp.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class FavoritescreenViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _favoriteMovieList = MutableStateFlow(listOf<Movie>())
    val favoriteMovieList: StateFlow<List<Movie>> = _favoriteMovieList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getFavorite().collect() { favoriteMovies ->
                if (!favoriteMovies.isNullOrEmpty()) {
                    _favoriteMovieList.value = favoriteMovies
                }
            }
        }
    }

    suspend fun toggleFavorite(movieID: String) {
        val movie = repository.getByID(movieID)
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }
}