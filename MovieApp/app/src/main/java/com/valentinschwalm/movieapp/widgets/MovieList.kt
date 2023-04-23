package com.valentinschwalm.movieapp.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.valentinschwalm.movieapp.models.Movie
import com.valentinschwalm.movieapp.navigation.movieID
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel
import kotlinx.coroutines.launch

@Composable
fun RenderMovieList(movies: State<List<Movie>>, onImageClick: (String) -> Unit, onFavoriteClick: (String) -> Unit) {

    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items(movies.value) {movie ->
            MovieRow (
                movie = movie,
                onImageClick = { movieID ->
                    onImageClick(movieID)
                },
                onFavoriteClick = {
                    onFavoriteClick.invoke(movie.id)
                }
            )
        }
    }
}