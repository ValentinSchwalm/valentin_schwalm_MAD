package com.valentinschwalm.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.valentinschwalm.movieapp.navigation.Screen
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel
import com.valentinschwalm.movieapp.widgets.RenderMovieList
import com.valentinschwalm.movieapp.widgets.SimpleAppBar

@Composable
fun FavoriteScreen(navController: NavController, viewModel: MoviesViewModel) {

    Column {
        SimpleAppBar(navController = navController, title = "Favorites")
        RenderMovieList (movies = viewModel.movieList.collectAsState(),
            onImageClick = { movieID ->
                navController.navigate(Screen.Detail.withArgs(movieID))
            },
            viewModel = viewModel
        )
    }
}
