package com.valentinschwalm.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.valentinschwalm.movieapp.enums.Screens
import com.valentinschwalm.movieapp.screens.DetailScreen
import com.valentinschwalm.movieapp.screens.FavoriteScreen
import com.valentinschwalm.movieapp.screens.HomeScreen

const val movieID: String = "movieId"

@Composable
fun MyNavigation() {

    val navController = rememberNavController()

    NavHost (
        navController = navController,
        startDestination = Screens.Homescreen.name
    ) {
        composable(route = Screens.Homescreen.name) {
            HomeScreen(navController = navController)
        }

        composable(
            route = "${Screens.Detailscreen.name}/{${movieID}}",
            arguments = listOf(navArgument(movieID) {
                type = NavType.StringType
            })
        ) {backStackEntry ->
            DetailScreen (
                navController = navController,
                movieID = backStackEntry.arguments?.getString(movieID)
            )
        }

        composable(route = Screens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }
    }
}