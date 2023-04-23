package com.valentinschwalm.movieapp.data

import androidx.room.*
import com.valentinschwalm.movieapp.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface IMovieDAO {
    @Insert suspend fun add(movie: Movie)

    @Update suspend fun update(movie: Movie)

    @Delete fun delete(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavorite(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id=:movieId")
    fun getByID(movieId: String): Movie
}