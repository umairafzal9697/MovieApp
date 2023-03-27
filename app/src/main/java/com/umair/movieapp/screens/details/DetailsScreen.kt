package com.umair.movieapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.umair.movieapp.model.Movie
import com.umair.movieapp.model.getMovies
import com.umair.movieapp.widgets.MovieRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, movieData: String?) {

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Magenta, elevation = 5.dp) {
               Row(horizontalArrangement = Arrangement.Start ) {
                   Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",
                  modifier = Modifier.clickable {
                       navController.popBackStack()
                   })

                   Spacer(modifier = Modifier.width(100.dp))

                   Text(text = "Movies")
               }

            }
        },
    ) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {

                Text(text = movieData.toString(), style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(23.dp))

          val filterdMovie=  getMovies().filter {
                  it.id ==movieData

                }
            
                MovieRow(movie = filterdMovie.first())
//                filterdMovie.map {
//                    MovieRow(movie = it)
//                }
                    Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images")

                HorizontalScrollAbleImageView(filterdMovie)



            }

        }
    }


}

@Composable
private fun HorizontalScrollAbleImageView(filterdMovie: List<Movie>) {
    LazyRow {
        items(filterdMovie[0].images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp), elevation = 5.dp
            ) {


                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "Movie Poster"
                )

            }

        }
    }
}