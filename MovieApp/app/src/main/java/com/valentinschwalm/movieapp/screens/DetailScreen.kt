package com.valentinschwalm.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.valentinschwalm.movieapp.models.getMovieByID
import com.valentinschwalm.movieapp.widgets.HorizontalDivider
import com.valentinschwalm.movieapp.widgets.ImageSlider
import com.valentinschwalm.movieapp.widgets.MovieRow
import com.valentinschwalm.movieapp.widgets.SimpleAppBar

@Composable
fun DetailScreen(navController: NavController, movieID: String?) {

    var movie = getMovieByID(movieID)

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        SimpleAppBar(navController, movie?.title)

        movie?.let {
            MovieRow(movie = movie) {}
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colors.secondary)
            Text(text = "Movie Images", modifier = Modifier.padding(5.dp),style = MaterialTheme.typography.h5)
            ImageSlider(movie = movie)
        }
    }
}


