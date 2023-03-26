package com.valentinschwalm.movieapp.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.valentinschwalm.movieapp.models.Movie
import com.valentinschwalm.movieapp.navigation.Screen
import com.valentinschwalm.movieapp.models.getMovies

@Composable
fun RenderMovieList(movies: List<Movie> = getMovies(), navController: NavController) {

    LazyColumn{
        items(movies) {movie ->
            MovieRow(movie = movie) { movieID ->
                navController.navigate(Screen.Detail.withArgs(movieID))
            }
        }
    }
}