package com.valentinschwalm.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun FavoriteScreen(navController: NavController) {

    Column {
        TopAppBarDetailScreen(navController = navController, title = "Favorites")
        MyList(navController = navController)
    }
}
