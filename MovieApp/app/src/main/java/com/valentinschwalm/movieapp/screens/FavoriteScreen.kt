package com.valentinschwalm.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel
import com.valentinschwalm.movieapp.widgets.RenderMovieList
import com.valentinschwalm.movieapp.widgets.SimpleAppBar

@Composable
fun FavoriteScreen(navController: NavController, viewModel: MoviesViewModel) {

    Column {
        SimpleAppBar(navController = navController, title = "Favorites")
        RenderMovieList(movies = viewModel.favoriteMovieList, navController = navController, viewModel = viewModel)
    }
}
