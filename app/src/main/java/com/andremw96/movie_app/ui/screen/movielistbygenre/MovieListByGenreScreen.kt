package com.andremw96.movie_app.ui.screen.movielistbygenre

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andremw96.core.domain.schema.Movie
import com.andremw96.movie_app.R
import com.andremw96.movie_app.ui.navgraph.NavGraphConstant
import com.andremw96.movie_app.ui.widget.InfiniteListHandler
import com.andremw96.movie_app.ui.widget.MovieAppBar
import com.andremw96.movie_app.ui.widget.MovieEmptyPage
import com.andremw96.movie_app.ui.widget.MovieErrorPage
import com.andremw96.movie_app.util.Util.fullPosterPhotoPath


@Composable
fun MovieListByGenreScreen(
    genreId: String,
    genreName: String,
    viewState: MovieListByGenreViewState,
    callbacks: MovieListByGenreCallbacks,
    navController: NavController,
) {
    val lazyListState = rememberLazyListState()

    Scaffold(topBar = {
        MovieAppBar(
            title = "${stringResource(id = R.string.movie_list_title)} $genreName",
            onBackClick = {
                navController.popBackStack()
            }
        )
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            when {
                viewState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                viewState.errorMessage != null -> {
                    MovieErrorPage(
                        errorMessage = viewState.errorMessage,
                        retryOnClick = {
                            callbacks.loadMovieListByGenre(genreId)
                        },
                    )
                }
                viewState.movieList.isEmpty() -> {
                    MovieEmptyPage(retryOnClick = {
                        callbacks.loadMovieListByGenre(genreId)
                    })
                }
                else -> {
                    LazyColumn(
                        state = lazyListState
                    ) {
                        items(viewState.movieList) {
                            MovieItem(
                                movie = it,
                                onItemClicked = {
                                    navController.navigate("${NavGraphConstant.MOVIE_DETAIL_BY_MOVIE_ID}/${it.id}")
                                }
                            )
                        }

                        if (viewState.isLoadingMore) {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }

                    InfiniteListHandler(lazyListState = lazyListState) {
                        callbacks.loadMoreMovieListByGenre(genreId)
                    }
                }
            }
        }
    })
}

@Composable
fun MovieItem(
    movie: Movie,
    onItemClicked: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onItemClicked()
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display the movie title
            Text(
                text = movie.title, style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display the movie poster image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterPath.fullPosterPhotoPath())
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
                text = movie.overview, style = MaterialTheme.typography.body2, color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display other movie details, e.g., release date, rating, etc.
            Text(
                text = "Release Date: ${movie.releaseDate}", style = MaterialTheme.typography.body2
            )
            Text(
                text = "Rating: ${movie.voteAverage}", style = MaterialTheme.typography.body2
            )
        }
    }
}
