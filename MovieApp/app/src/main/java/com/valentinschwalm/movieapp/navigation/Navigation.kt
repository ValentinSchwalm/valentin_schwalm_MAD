package com.valentinschwalm.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.valentinschwalm.movieapp.data.MovieDatabase
import com.valentinschwalm.movieapp.models.MovieViewModelFactory
import com.valentinschwalm.movieapp.repositories.MovieRepository
import com.valentinschwalm.movieapp.screens.AddMovieScreen
import com.valentinschwalm.movieapp.screens.DetailScreen
import com.valentinschwalm.movieapp.screens.FavoriteScreen
import com.valentinschwalm.movieapp.screens.HomeScreen
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel

const val movieID = "movieID"

@Composable
fun MyNavigation() {
    val navController = rememberNavController()

    val database = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(database.movieDAO())
    val factory = MovieViewModelFactory(repository)

    val movieViewModel: MoviesViewModel = viewModel(factory = factory)

    NavHost (
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, movieViewModel)
        }

        composable (
            route = Screen.Detail.route + "/{$movieID}",
            arguments = listOf(navArgument(movieID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen (
                navController = navController,
                viewModel = movieViewModel,
                movieID = backStackEntry.arguments?.getString(movieID)
            )
        }

        composable(route = Screen.Favorite.route) {
            FavoriteScreen(navController = navController, viewModel = movieViewModel)
        }

        composable(route = Screen.AddMovie.route) {
            AddMovieScreen(navController = navController, viewModel = movieViewModel)
        }
    }
}