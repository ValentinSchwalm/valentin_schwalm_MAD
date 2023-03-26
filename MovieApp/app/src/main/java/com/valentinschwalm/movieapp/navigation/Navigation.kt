package com.valentinschwalm.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.valentinschwalm.movieapp.screens.DetailScreen
import com.valentinschwalm.movieapp.screens.FavoriteScreen
import com.valentinschwalm.movieapp.screens.HomeScreen

const val movieID = "movieID"

@Composable
fun MyNavigation() {

    val navController = rememberNavController()

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
                movieID = backStackEntry.arguments?.getString(movieID)
            )
        }

        composable(route = Screen.Favorite.route) {
            FavoriteScreen(navController = navController)
        }
    }
}