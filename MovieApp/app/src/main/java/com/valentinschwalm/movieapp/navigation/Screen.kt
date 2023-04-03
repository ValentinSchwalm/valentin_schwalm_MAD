package com.valentinschwalm.movieapp.navigation

sealed class Screen(val route: String) {

    object Home: Screen("home")
    object Detail: Screen("detail")
    object Favorite: Screen("favorite")
    object AddMovie: Screen("addMovie")

    fun withArgs(vararg args: String): String {

        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}