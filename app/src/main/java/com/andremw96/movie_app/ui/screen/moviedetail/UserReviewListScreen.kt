package com.andremw96.movie_app.ui.screen.moviedetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andremw96.movie_app.R
import com.andremw96.movie_app.ui.widget.InfiniteListHandler
import com.andremw96.movie_app.ui.widget.MovieAppBar
import com.andremw96.movie_app.ui.widget.MovieEmptyPage
import com.andremw96.movie_app.ui.widget.MovieErrorPage


@Composable
fun UserReviewListScreen(
    viewState: MovieDetailViewState,
    callbacks: MovieDetailCallbacks,
    navController: NavController,
) {
    val lazyListState = rememberLazyListState()
    val movieId = viewState.movieDetail?.id.toString()

    Scaffold(topBar = {
        MovieAppBar(
            title = "${stringResource(id = R.string.user_reviews_title)} ${viewState.movieDetail?.originalTitle}",
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
                            callbacks.loadMovieReviewListByMovieId(movieId)
                        },
                    )
                }
                viewState.userReviews.isEmpty() -> {
                    MovieEmptyPage(retryOnClick = {
                        callbacks.loadMovieReviewListByMovieId(movieId)
                    })
                }
                else -> {
                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        items(viewState.userReviews) {
                            ReviewItem(
                                review = it
                            )
                        }

                        if (viewState.isLoadingMoreUserReviews) {
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
                        callbacks.loadMoreMovieReviewListByMovieId(movieId)
                    }
                }
            }
        }
    })
}
