package com.valentinschwalm.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.valentinschwalm.movieapp.screens.AddMovieScreen
import com.valentinschwalm.movieapp.screens.DetailScreen
import com.valentinschwalm.movieapp.screens.FavoriteScreen
import com.valentinschwalm.movieapp.screens.HomeScreen
import com.valentinschwalm.movieapp.utils.InjectorUtils
import com.valentinschwalm.movieapp.viewmodels.DetailscreenViewModel

const val movieID = "movieID"

@Composable
fun MyNavigation() {
    val navController = rememberNavController()

    val detailscreenViewModel: DetailscreenViewModel = viewModel(
        factory = InjectorUtils.provideDetailScreenViewModelFactory(LocalContext.current)
    )

    NavHost (
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable (
            route = Screen.Detail.route + "/{$movieID}",
            arguments = listOf(navArgument(movieID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen (
                navController = navController,
                viewModel = detailscreenViewModel,
                movieID = backStackEntry.arguments?.getString(movieID)
            )
        }

        composable(route = Screen.Favorite.route) {
            FavoriteScreen(navController = navController)
        }

        composable(route = Screen.AddMovie.route) {
            AddMovieScreen(navController = navController)
        }
    }
}