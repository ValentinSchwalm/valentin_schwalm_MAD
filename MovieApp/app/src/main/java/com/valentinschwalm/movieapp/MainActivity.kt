package com.valentinschwalm.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.valentinschwalm.movieapp.navigation.MyNavigation
import com.valentinschwalm.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            MovieAppTheme {

                DefaultPreview()
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {

    MyNavigation()
}