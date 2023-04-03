package com.valentinschwalm.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.valentinschwalm.movieapp.models.MovieRepository
import com.valentinschwalm.movieapp.screens.AddMovieScreen
import com.valentinschwalm.movieapp.screens.DetailScreen
import com.valentinschwalm.movieapp.screens.FavoriteScreen
import com.valentinschwalm.movieapp.screens.HomeScreen
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel

const val movieID = "movieID"

@Composable
fun MyNavigation() {

    val movieRepository: MovieRepository = MovieRepository()
    val navController = rememberNavController()
    val moviesViewModel: MoviesViewModel = MoviesViewModel(movieRepository = movieRepository)

    NavHost (
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, viewModel = moviesViewModel)
        }

        composable (
            route = Screen.Detail.route + "/{$movieID}",
            arguments = listOf(navArgument(movieID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen (
                navController = navController,
                viewModel = moviesViewModel,
                movieID = backStackEntry.arguments?.getString(movieID)
            )
        }

        composable(route = Screen.Favorite.route) {
            FavoriteScreen(navController = navController, viewModel = moviesViewModel)
        }

        composable(route = Screen.AddMovie.route) {
            AddMovieScreen(navController = navController)
        }
    }
}