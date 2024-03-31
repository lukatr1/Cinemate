package com.example.movieappmad24.ui.screens

import SimpleTopMovieAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieappmad24.components.MovieCard
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            SimpleTopMovieAppBar(
                title = "Home", onBackPressed = {})

        },
    ) { innerPadding ->
        MovieList(
            movies = getMovies(),
            navController = navController,
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(innerPadding)
        )
    }
}

@Composable
fun MovieList(movies: List<Movie>, navController: NavHostController, modifier: Modifier = Modifier) {
    val expandedMovieId = remember { mutableStateOf<String?>(null) }

    LazyColumn(modifier = modifier.padding(horizontal = 7.dp)) {
        items(movies) { movie ->
            MovieCard(
                movie = movie,
                expanded = expandedMovieId.value == movie.id,
                onExpandToggle = {
                    expandedMovieId.value = expandedMovieId.value?.takeUnless { it == movie.id } ?: movie.id
                },
                navController = navController
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}





