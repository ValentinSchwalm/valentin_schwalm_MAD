package com.valentinschwalm.movieapp
import com.valentinschwalm.movieapp.models.Movie

interface IMovieRepository {
    fun getMovies() : List<Movie>
}