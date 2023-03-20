package com.valentinschwalm.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.valentinschwalm.movieapp.enums.Screens
import com.valentinschwalm.movieapp.screens.DetailScreen
import com.valentinschwalm.movieapp.screens.HomeScreen

@Composable
fun MyNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Screens.Homescreen.name ) {

        composable(Screens.Homescreen.name) { HomeScreen(navController = navController)}
        composable(Screens.Detailscreen.name) { DetailScreen(navController = navController)}
    }
}