package com.example.movieappmad24

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    Scaffold(
                        topBar = { AppTopBar() },

                        //bottomBar = { AppBottomBar() }
                        bottomBar = {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = false,
                                    onClick = { /*TODO*/ },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Home,
                                            contentDescription = "Home"
                                        )
                                    },
                                    label = { Text("Home") }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = { /*TODO*/ },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Outlined.FavoriteBorder,
                                            contentDescription = "Watchlist"
                                        )
                                    },
                                    label = { Text("Watchlist") }
                                )
                            }
                        }
                    )
                    {
                        MovieContent()

                    }
                }
            }
        }
    }
}

@Composable
fun AppTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Cinemate",
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun MovieContent() {
    val movies = getMovies()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp, vertical = 65.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(movies) { movie ->
                MovieCard(movie = movie)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun MovieCard(movie: Movie) {
    var expanded by remember { mutableStateOf(false) }
    //val arrowRotation by animateDpAsState(targetValue = if (expanded) 180.dp else 0.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        //elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {

            Box(modifier = Modifier)
            {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = null,
                    contentScale = FillWidth,
                    modifier = Modifier
                        .aspectRatio(ratio =19f / 7f)
                )
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "likeBtn",
                    tint = Color.White,
                    modifier = Modifier
                        .size(27.dp)
                        .align(Alignment.TopEnd)
                        // add padding (don't know if this should be done this way)
                        .offset(x = (-15).dp, y = 15.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Display movie title
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Expand/Collapse",
                modifier = Modifier
                    .padding(8.dp)
                    .size(36.dp)
                    .clickable { expanded = !expanded }
                /*.graphicsLayer {
                    rotationX = arrowRotation.180F
                }*/
            )
        }
        // Display movie details if expanded
        AnimatedVisibility(visible = expanded, enter = expandVertically(), exit = shrinkVertically()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Genre: ${movie.genre}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Actors: ${movie.actors}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Rating: ${movie.rating}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Plot: ${movie.plot}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

    }
}






