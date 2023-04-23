package com.valentinschwalm.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.valentinschwalm.movieapp.R
import com.valentinschwalm.movieapp.models.Genre
import com.valentinschwalm.movieapp.models.ListItemSelectable
import com.valentinschwalm.movieapp.models.Movie
import com.valentinschwalm.movieapp.navigation.Screen
import com.valentinschwalm.movieapp.utils.InjectorUtils
import com.valentinschwalm.movieapp.viewmodels.AddMovieViewModel
import com.valentinschwalm.movieapp.viewmodels.MoviesViewModel
import com.valentinschwalm.movieapp.widgets.SimpleAppBar
import kotlinx.coroutines.launch

@Composable
fun AddMovieScreen(navController: NavController) {

    val viewModel: AddMovieViewModel = viewModel(
        factory = InjectorUtils.provideAddMovieScreenViewModelFactory(LocalContext.current)
    )

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleAppBar(navController = navController, title = "Add Movie")
        },
    ) { padding ->
        MainContent(Modifier.padding(padding), viewModel, navController)
    }
}

@Composable
private fun DrawWarning(message: String) {

    Row(modifier = Modifier.padding(horizontal = 0.dp, vertical = 5.dp)) {
        Icon (
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 0.dp),
            imageVector = Icons.Default.Warning,
            contentDescription = "error icon",
            tint = Color(255,204,0)
        )

        Text (
            text = message,
            fontWeight = FontWeight.Bold,
            color = Color(255,204,0)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(modifier: Modifier = Modifier, viewModel: AddMovieViewModel, navController: NavController) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            OutlinedTextField(
                value = viewModel.title.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.title.value = it
                    viewModel.validateTitle()
                },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = viewModel.isTitleValid
            )
            
            if (!viewModel.isTitleValid) {
                DrawWarning(message = "title is required")
            }

            OutlinedTextField(
                value = viewModel.year.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.year.value = it
                    viewModel.validateYear()
                },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = viewModel.isYearValid
            )

            if (!viewModel.isYearValid) {
                DrawWarning(message = "year is required")
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(viewModel.genreItems.value) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            viewModel.genreItems.value = viewModel.genreItems.value.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }

                            viewModel.validateGenres()
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            if (!viewModel.isGenresValid) {
                DrawWarning(message = "at least one genre is required")
            }

            OutlinedTextField(
                value = viewModel.director.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.director.value = it
                    viewModel.validateDirector()
                },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = viewModel.isDirectorValid
            )

            if (!viewModel.isDirectorValid) {
                DrawWarning(message = "director is required")
            }

            OutlinedTextField(
                value = viewModel.actors.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.actors.value = it
                    viewModel.validateActors()
                },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = viewModel.isActorsValid
            )

            if (!viewModel.isActorsValid) {
                DrawWarning(message = "at least one actor is required")
            }

            OutlinedTextField(
                value = viewModel.plot.value,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { viewModel.plot.value = it },
                label = { Text(textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)) },
                isError = false
            )

            OutlinedTextField(
                value = viewModel.rating.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.rating.value = if(it.startsWith("0")) "" else it
                    viewModel.validateRating()
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = viewModel.isRatingValid
            )

            if (!viewModel.isRatingValid) {
                DrawWarning(message = "rating in form of a number required")
            }

            val coroutineScope = rememberCoroutineScope()

            Button(
                enabled = viewModel.isAddButtonEnabled.value,
                onClick = {
                    coroutineScope.launch {
                        viewModel.addMovie()
                    }

                    navController.navigate(Screen.Home.route)
                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}