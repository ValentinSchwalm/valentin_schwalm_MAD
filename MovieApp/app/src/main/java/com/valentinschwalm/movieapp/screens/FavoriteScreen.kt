package com.valentinschwalm.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.valentinschwalm.movieapp.navigation.Screen
import com.valentinschwalm.movieapp.utils.InjectorUtils
import com.valentinschwalm.movieapp.viewmodels.FavoritescreenViewModel
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel
import com.valentinschwalm.movieapp.widgets.RenderMovieList
import com.valentinschwalm.movieapp.widgets.SimpleAppBar
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController) {

    val viewModel: FavoritescreenViewModel = viewModel(
        factory = InjectorUtils.provideFavoriteScreenViewModelFactory(LocalContext.current)
    )

    var coroutineScope = rememberCoroutineScope()

    Column {
        SimpleAppBar(navController = navController, title = "Favorites")
        RenderMovieList (movies = viewModel.favoriteMovieList.collectAsState(),
            onImageClick = { movieID ->
                navController.navigate(Screen.Detail.withArgs(movieID))
            },
            onFavoriteClick = { movieID ->
                coroutineScope.launch {
                    viewModel.toggleFavorite(movieID)
                }
            }
        )
    }
}
