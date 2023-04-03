package com.valentinschwalm.movieapp.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.valentinschwalm.movieapp.models.DropDownItem
import com.valentinschwalm.movieapp.navigation.Screen
import com.valentinschwalm.movieapp.ui.theme.Purple700
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel
import com.valentinschwalm.movieapp.widgets.RenderMovieList

@Composable
fun HomeScreen(navController: NavController, viewModel: MoviesViewModel) {

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TopAppBar(navController = navController)
            RenderMovieList(movies = viewModel.movieList, navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
private fun TopAppBar(navController: NavController) {

    var items = listOf (
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
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var menuIcon by remember { mutableStateOf(Icons.Default.Menu) }
    
    Box(modifier = Modifier
        .background(Purple700)
        .fillMaxWidth(),
        contentAlignment = Alignment.TopEnd) {

        IconButton(onClick = {
            expanded = true
            menuIcon = Icons.Default.Close
        }) {
            Icon (
                imageVector = menuIcon,
                contentDescription = "Open Options",
                tint = Color.White
            )
        }

        DropdownMenu (
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                menuIcon = Icons.Default.Menu
           },
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple700)
                .animateContentSize(
                    animationSpec = tween( easing = LinearOutSlowInEasing )
                )
        ) {

            items.forEachIndexed { index, item ->
                TopAppBarItem(navController = navController, item = item) {
                    selectedIndex = index
                    expanded = false
                    menuIcon = Icons.Default.Menu
                }
            }
        }
    }
}

@Composable
private fun TopAppBarItem(navController: NavController, item: DropDownItem, onMenuClick: () -> Unit) {

    DropdownMenuItem (onClick = { onMenuClick() }) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(item.route)
                },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text (
                text = item.name,
                color = Color.White,
                textAlign = TextAlign.Right
            )

            Icon (
                imageVector = item.icon,
                contentDescription = "Open Options",
                modifier = Modifier.padding(10.dp),
                tint = Color.White
            )
        }
    }
}
