package com.valentinschwalm.movieapp.utils

import android.content.Context
import com.valentinschwalm.movieapp.data.MovieDatabase
import com.valentinschwalm.movieapp.repositories.MovieRepository
import com.valentinschwalm.movieapp.viewmodels.*

object InjectorUtils {
    private fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepository(MovieDatabase.getDatabase(context).movieDAO())
    }

    fun provideHomeScreenViewModelFactory(context: Context): HomescreenFactory {
        val repository = getMovieRepository(context)
        return HomescreenFactory(repository)
    }

    fun provideFavoriteScreenViewModelFactory(context: Context): FavoritescreenFactory {
        val repository = getMovieRepository(context)
        return FavoritescreenFactory(repository)
    }

    fun provideDetailScreenViewModelFactory(context: Context): DetailscreenFactory {
        val repository = getMovieRepository(context)
        return DetailscreenFactory(repository)
    }

    fun provideAddMovieScreenViewModelFactory(context: Context): AddMoviescreenFactory {
        val repository = getMovieRepository(context)
        return AddMoviescreenFactory(repository)

    }
}