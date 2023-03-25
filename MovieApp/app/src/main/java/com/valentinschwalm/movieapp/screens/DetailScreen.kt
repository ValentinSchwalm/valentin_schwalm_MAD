package com.valentinschwalm.movieapp.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.valentinschwalm.movieapp.R
import com.valentinschwalm.movieapp.assets.Movie
import com.valentinschwalm.movieapp.assets.getMovieByID
import com.valentinschwalm.movieapp.assets.getMovies
import com.valentinschwalm.movieapp.enums.Screens
import com.valentinschwalm.movieapp.ui.theme.Purple700

@Composable
fun DetailScreen(navController: NavController, movieID: String?) {

    var movie = getMovieByID(movieID)

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBarDetailScreen(navController, movie?.title)

        movie?.let {
            MovieRow(movie = movie) {}
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colors.secondary)
            Text(text = "Movie Images", modifier = Modifier.padding(5.dp),style = MaterialTheme.typography.h5)
            MovieImageSlider(movie = movie)
        }
    }

}

@Composable
fun TopAppBarDetailScreen(navController: NavController, title: String?) {

    Box(modifier = Modifier
        .background(Purple700)
        .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart) {

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon (
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Open Options",
                    tint = Color.White
                )
            }

            title?.let {
                Text (
                    text = title,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun MovieImageSlider(movie: Movie) {
    LazyRow {
        items(movie.images) {movieImage ->
            val painter = rememberImagePainter(
                data = movieImage,
                builder = {
                    placeholder(R.drawable.imageplaceholder)
                    error(R.drawable.imageerror)
                    crossfade(300)
                }
            )
            ImageCard(painter = painter)
        }
    }
}

@Composable
fun ImageCard(painter: Painter) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, 10.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier
            .height(200.dp)
            .width(370.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "Movie Poster",
                contentScale = ContentScale.Crop
            )
        }
    }
}