package com.andremw96.movie_app.ui.screen.moviedetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andremw96.movie_app.ui.widget.MovieAppBar
import com.andremw96.movie_app.ui.widget.MovieEmptyPage
import com.andremw96.movie_app.ui.widget.MovieErrorPage

@Composable
fun MovieDetailScreen(
    movieId: String,
    viewState: MovieDetailViewState,
    callbacks: MovieDetailCallbacks,
    navController: NavController,
) {
    Scaffold(
        topBar = {
            MovieAppBar(title = "Detail Movie of ${viewState.movieDetail?.originalTitle}", onBackClick = {
                navController.popBackStack()
            })
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.background
        ) {
            when {
                viewState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                viewState.errorMessage != null -> {
                    MovieErrorPage(
                        errorMessage = viewState.errorMessage,
                        retryOnClick = {
                            callbacks.loadMovieDetailByMovieId(movieId)
                        },
                    )
                }
                viewState.movieDetail == null -> {
                    MovieEmptyPage(retryOnClick = {
                        callbacks.loadMovieDetailByMovieId(movieId)
                    })
                }
                else -> {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                // Display the movie poster image
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("https://image.tmdb.org/t/p/w500/${viewState.movieDetail.posterPath}")
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(200.dp)
                                        .fillMaxWidth(),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                // Display the movie overview
                                Text(
                                    text = "Overview",
                                    style = MaterialTheme.typography.h6
                                )
                                Text(
                                    text = viewState.movieDetail.overview,
                                    style = MaterialTheme.typography.body1,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                // Display other movie details, e.g., release date, rating, etc.
                                Text(
                                    text = "Release Date: ${viewState.movieDetail.releaseDate}",
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                                Text(
                                    text = "Rating: ${viewState.movieDetail.voteAverage}",
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            }
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "User Reviews",
                                    style = MaterialTheme.typography.h6
                                )
                                // Iterate through user reviews and display them
//                                movie.userReviews.forEach { review ->
//                                    ReviewItem(review = review)
//                                }

                                Button(
                                    onClick = {
                                    },
                                    modifier = Modifier.padding(top = 16.dp),
                                ) {
                                    Text(text = "See All")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
