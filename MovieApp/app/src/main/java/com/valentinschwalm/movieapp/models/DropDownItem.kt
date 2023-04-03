package com.valentinschwalm.movieapp.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.valentinschwalm.movieapp.navigation.Screen

data class DropDownItem (
    val name: String,
    val icon: ImageVector,
    val route: String
)
/*
fun getDropDownItems() : List<DropDownItem> {

    return listOf (
        DropDownItem (
            name = "Favorites",
            icon = Icons.Default.Favorite,
            route = Screen.Favorite.route
        ),
        DropDownItem (
            name = "Add Movie",
            icon = Icons.Default.AddCircle,
            route = Screen.AddMovie.route
        )
    )
}

 */