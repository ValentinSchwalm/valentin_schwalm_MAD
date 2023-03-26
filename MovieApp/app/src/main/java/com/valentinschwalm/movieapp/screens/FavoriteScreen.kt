package com.valentinschwalm.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.valentinschwalm.movieapp.widgets.RenderMovieList
import com.valentinschwalm.movieapp.widgets.SimpleAppBar

@Composable
fun FavoriteScreen(navController: NavController) {

    Column {
        SimpleAppBar(navController = navController, title = "Favorites")
        RenderMovieList(navController = navController)
    }
}
