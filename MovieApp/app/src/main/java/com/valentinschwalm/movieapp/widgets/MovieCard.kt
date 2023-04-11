package com.valentinschwalm.movieapp.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinschwalm.movieapp.models.Movie
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel

@Composable
fun MovieRow(movie: Movie, onImageClick: (String) -> Unit, onFavoriteClick: () -> Movie) {

    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState ( targetValue = if (expanded) -180f else 0f )

    Card(modifier = Modifier
        .animateContentSize(
            animationSpec = tween(
                delayMillis = 50,
                easing = LinearOutSlowInEasing
            )
        )
        .fillMaxWidth()
        .padding(5.dp, 10.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            MovieImage(movie = movie, onImageClick = onImageClick, onFavoriteClick = onFavoriteClick)

            MovieExpandable(movie = movie, rotationState = rotationState) {
                expanded = !expanded
            }

            if (expanded) {
                MovieDetails(movie = movie)
            }
        }
    }
}

@Composable
private fun MovieImage (movie: Movie, onImageClick: (String) -> Unit, onFavoriteClick: () -> Movie) {

    var isFavorite by remember { mutableStateOf(movie.isFavorite) }
    var _movie by remember { mutableStateOf(movie) }

    Box(modifier = Modifier
        .height(150.dp)
        .fillMaxWidth()
        .clickable { onImageClick(movie.id) }
    ) {
        WebImage(imageUrl = if (movie.images.isNotEmpty()) movie.images[0] else null, imageDescription = "Movie Poster")

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(onClick = {
                _movie = onFavoriteClick()
                isFavorite = _movie.isFavorite
            }) {

                Icon(
                    tint = if (_movie.id == movie.id) (if (isFavorite) Color.Red else MaterialTheme.colors.secondary) else Color.Red,
                    imageVector = if (_movie.id == movie.id) (if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder) else Icons.Default.Favorite,
                    contentDescription = "Add to favorites"
                )
            }
        }
    }
}

@Composable
private fun MovieExpandable (movie: Movie, rotationState: Float, onArrowClick: () -> Unit) {

    Row(modifier = Modifier
        .height(60.dp)
        .fillMaxWidth()
        .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.h6
        )
        IconButton(
            modifier = Modifier
                .rotate(rotationState),
            onClick = {
                onArrowClick()
            }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Show details"
            )
        }
    }
}

@Composable
private fun MovieDetails(movie: Movie) {

    Text (
        text = "Director: ${movie.director} \n" +
                "Genre: ${movie.genre.joinToString()} \n" +
                "Release: ${movie.year}",
        modifier = Modifier.padding(15.dp),
        style = MaterialTheme.typography.subtitle1
    )

    HorizontalDivider(1.dp, Color.Black)

    Text (
        text = "Plot: ${movie.plot}",
        modifier = Modifier.padding(15.dp),
        style = MaterialTheme.typography.subtitle1
    )
}