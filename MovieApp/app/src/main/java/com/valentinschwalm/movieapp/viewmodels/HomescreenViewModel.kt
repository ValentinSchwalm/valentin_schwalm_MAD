package com.valentinschwalm.movieapp.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.valentinschwalm.movieapp.models.Movie

/*
class HomescreenViewModel constructor(repository: MovieRepository) : ViewModel() {

    private val movieRepository = repository

    // movie list
    private val _movieList = movieRepository.getMovies()
    val movieList: List<Movie>
        get() = _movieList

    fun getMovieByID(movieID: String?): Movie? {
        val filteredMovies = _movieList.filter { it.id == movieID }
        return if (filteredMovies.isNotEmpty()) filteredMovies[0] else null
    }

    // favorite list
    private val _favoriteMovieList = _movieList.filter { it.isFavorite }.toMutableStateList()
    val favoriteMovieList: List<Movie>
        get() = _favoriteMovieList

    fun toggleFavorite(movie: Movie): Movie {

        movie.isFavorite = !movie.isFavorite
        if (_favoriteMovieList.contains(movie)) _favoriteMovieList.remove(movie) else _favoriteMovieList.add(movie)
        return movie
    }
}

 */