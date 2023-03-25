package com.valentinschwalm.movieapp.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.valentinschwalm.movieapp.R
import com.valentinschwalm.movieapp.assets.Movie
import com.valentinschwalm.movieapp.assets.getMovies
import com.valentinschwalm.movieapp.enums.Screens
import com.valentinschwalm.movieapp.ui.theme.Purple200
import com.valentinschwalm.movieapp.ui.theme.Purple700

@Composable
fun HomeScreen(navController: NavController) {

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TopAppBar(navController = navController)
            MyList(navController = navController)
        }
    }
}

@Composable
fun TopAppBar(navController: NavController) {

    var items = listOf("Favorites")
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
            Icon(
                imageVector = menuIcon,
                contentDescription = "Open Options",
                tint = Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                menuIcon = Icons.Default.Menu
           },
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple700)
        ) {

            items.forEachIndexed { index, itemName ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    menuIcon = Icons.Default.Menu
                }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                       navController.navigate(Screens.FavoriteScreen.name)
                            },
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Open Options",
                            modifier = Modifier.padding(10.dp),
                            tint = Color.White
                        )
                        Text(text = itemName, color = Color.White, textAlign = TextAlign.Right)
                    }
                }
            }
        }
    }
}

@Composable
fun MyList(movies: List<Movie> = getMovies(), navController: NavController){

    LazyColumn{

        items(movies) {movie ->
            MovieRow(movie = movie) { movieId ->
                Log.d("MainContent", "My Callback value: $movieId")
                navController.navigate("${Screens.Detailscreen.name}/${movieId}")
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) -180f else 0f
    )

    Card(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                delayMillis = 300,
                easing = LinearOutSlowInEasing
            )
        )
        .padding(5.dp, 10.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clickable { onItemClick(movie.id) }
            ) {
                val painter = rememberImagePainter(
                    data = if (movie.images.isNotEmpty()) movie.images[0] else R.drawable.imageerror,
                    builder = {
                        placeholder(R.drawable.imageplaceholder)
                        error(R.drawable.imageerror)
                        crossfade(300)
                    }
                )

                Image(
                    painter = painter,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites"
                    )
                }
            }

            Row(modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6
                )
                IconButton(
                    modifier = Modifier
                        .rotate(rotationState),
                    onClick = {
                        expanded = !expanded
                    }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Show details"
                    )
                }

            }

            if (expanded) {
                Text(
                    text = "Director: ${movie.director} \n" +
                            "Genre: ${movie.genre} \n" +
                            "Release: ${movie.year}",
                    modifier = Modifier
                        .padding(15.dp),
                    style = MaterialTheme.typography.subtitle1
                )

                HorizontalDivider(1.dp, Color.Black)

                Text(
                    text = "Plot: ${movie.plot}",
                    modifier = Modifier
                        .padding(15.dp),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@Composable
fun HorizontalDivider(thickness: Dp, color: Color) {
    Column(
        modifier = Modifier.padding(10.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Divider(thickness = thickness, color = color)
    }
}