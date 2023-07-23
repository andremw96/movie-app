package com.andremw96.movie_app.ui.screen.genrelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andremw96.core.domain.schema.Genre
import com.andremw96.movie_app.ui.navgraph.NavGraphConstant
import com.andremw96.movie_app.ui.widget.MovieAppBar
import com.andremw96.movie_app.ui.widget.MovieEmptyPage
import com.andremw96.movie_app.ui.widget.MovieErrorPage
import com.andremw96.movie_app.R


@Composable
fun GenreListScreen(
    viewState: GenreListViewState,
    callbacks: GenreListCallbacks,
    navController: NavController,
) {
    Scaffold(
        topBar = {
            MovieAppBar(
                title = stringResource(id = R.string.genre_list_title),
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
            ) {
                Spacer(modifier = Modifier.height(8.dp))

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
                                callbacks.loadGenreList()
                            },
                        )
                    }
                    viewState.genreList.isEmpty() -> {
                        MovieEmptyPage(retryOnClick = {
                            callbacks.loadGenreList()
                        })
                    }
                    else -> {
                        LazyColumn {
                            items(viewState.genreList) {
                                GenreItem(
                                    genre = it,
                                    onItemClicked = {
                                        // go to movie list by genre screen
                                        navController.navigate("${NavGraphConstant.MOVIE_LIST_BY_GENRE}/${it.id}/${it.name}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun GenreItem(
    genre: Genre,
    onItemClicked: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onItemClicked()
            },
        elevation = 4.dp
    ) {
        Text(
            text = genre.name,
            modifier = Modifier.padding(16.dp)
        )
    }
}
