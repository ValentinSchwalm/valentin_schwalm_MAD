package com.valentinschwalm.movieapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.valentinschwalm.movieapp.R

@Composable
fun WebImage(imageUrl: String?, imageDescription: String) {

    val painter = rememberImagePainter (
        data = imageUrl ?: R.drawable.imageerror,
        builder = {
            placeholder(R.drawable.imageplaceholder)
            error(R.drawable.imageerror)
            crossfade(300)
        }
    )

    Image (
        painter = painter,
        contentDescription = imageDescription,
        contentScale = ContentScale.Crop
    )
}