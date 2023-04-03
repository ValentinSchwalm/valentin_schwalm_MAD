package com.valentinschwalm.movieapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.valentinschwalm.movieapp.IMovieRepository
import com.valentinschwalm.movieapp.models.Movie

class MoviesViewModel constructor(movieRepository: IMovieRepository): ViewModel() {
    private val _movieRepository = movieRepository

    private val _movieList = _movieRepository.getMovies().toMutableList()
    val movieList: List<Movie>
        get() = _movieList

    private val _favoriteMovieList = getFavoriteMovies().toMutableList()
    val favoriteMovieList: List<Movie>
        get() = _favoriteMovieList

    fun toggleFavorite(movie: Movie): Boolean {
        if (_favoriteMovieList.contains(movie)) _favoriteMovieList.remove(movie) else _favoriteMovieList.add(movie)
        movie.isFavorite = !movie.isFavorite
        return movie.isFavorite
    }

    fun getMovieByID(movieID: String?): Movie? {
        var filteredMovies = _movieList.filter { it.id == movieID }
        return if (filteredMovies.isNotEmpty()) filteredMovies[0] else null
    }

    private fun getFavoriteMovies(): List<Movie> {
        return _movieList.filter { it.isFavorite }
    }
}