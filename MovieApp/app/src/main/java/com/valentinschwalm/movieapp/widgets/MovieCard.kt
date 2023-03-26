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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinschwalm.movieapp.models.Movie

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit) {

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
            MovieImage(movie = movie, onItemClick = onItemClick)

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
private fun MovieImage (movie: Movie, onItemClick: (String) -> Unit) {

    Box(modifier = Modifier
        .height(150.dp)
        .fillMaxWidth()
        .clickable { onItemClick(movie.id) }
    ) {
        WebImage(imageUrl = if (movie.images.isNotEmpty()) movie.images[0] else null, imageDescription = "Movie Poster")

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon (
                tint = MaterialTheme.colors.secondary,
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Add to favorites"
            )
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
                "Genre: ${movie.genre} \n" +
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