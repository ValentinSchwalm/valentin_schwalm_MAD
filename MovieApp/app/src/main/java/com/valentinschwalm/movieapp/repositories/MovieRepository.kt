package com.valentinschwalm.movieapp.repositories

import com.valentinschwalm.movieapp.data.IMovieDAO
import com.valentinschwalm.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDAO: IMovieDAO) {
    suspend fun add(movie: Movie) = movieDAO.add(movie)

    suspend fun update(movie: Movie) = movieDAO.update(movie)

    fun delete(movie: Movie) = movieDAO.delete(movie)

    fun getAll(): Flow<List<Movie>> = movieDAO.getAll()

    fun getFavorite(): Flow<List<Movie>> = movieDAO.getFavorite()

    suspend fun getByID(movieID: String): Movie = movieDAO.getByID(movieID)
}