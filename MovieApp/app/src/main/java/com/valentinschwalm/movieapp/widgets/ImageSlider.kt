package com.valentinschwalm.movieapp.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valentinschwalm.movieapp.models.Movie

@Composable
fun ImageSlider(movie: Movie) {

    LazyRow {
        items(movie.images) {movieImage ->
            ImageCard(imageUrl = movieImage)
        }
    }
}


@Composable
private fun ImageCard(imageUrl: String) {

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
            WebImage(imageUrl = imageUrl, imageDescription = "Movie Image")
        }
    }
}