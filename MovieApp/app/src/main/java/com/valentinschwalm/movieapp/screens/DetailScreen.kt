package com.valentinschwalm.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.valentinschwalm.movieapp.utils.InjectorUtils
import com.valentinschwalm.movieapp.viewmodels.DetailscreenFactory
import com.valentinschwalm.movieapp.viewmodels.DetailscreenViewModel
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel
import com.valentinschwalm.movieapp.widgets.HorizontalDivider
import com.valentinschwalm.movieapp.widgets.ImageSlider
import com.valentinschwalm.movieapp.widgets.MovieRow
import com.valentinschwalm.movieapp.widgets.SimpleAppBar
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navController: NavController, viewModel: DetailscreenViewModel, movieID: String?) {

    var movie = movieID?.let { viewModel.getMovieByID(movieID) }

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        SimpleAppBar(navController, movie?.title)
        val coroutineScope = rememberCoroutineScope()

        movie?.let {
            MovieRow (
                movie = movie,
                onImageClick = { movieID -> println("movie: $movieID") },
                onFavoriteClick = {
                    coroutineScope.launch {
                        viewModel.toggleFavorite(movie.id)
                    }
                }
            )

            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colors.secondary)
            Text(text = "Movie Images", modifier = Modifier.padding(5.dp),style = MaterialTheme.typography.h5)
            ImageSlider(movie = movie)
        }
    }
}


