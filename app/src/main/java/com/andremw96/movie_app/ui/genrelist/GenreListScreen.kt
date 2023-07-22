package com.andremw96.movie_app.ui.genrelist

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
import androidx.compose.ui.unit.dp
import com.andremw96.core.domain.schema.Genre
import com.andremw96.movie_app.ui.widget.MovieAppBar
import com.andremw96.movie_app.ui.widget.MovieEmptyPage
import com.andremw96.movie_app.ui.widget.MovieErrorPage


@Composable
fun GenreListScreen(
    viewState: GenreListViewState,
    callbacks: GenreListCallbacks,
) {
    Scaffold(
        topBar = {
            MovieAppBar()
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
                                GenreItem(genre = it)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun GenreItem(genre: Genre) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Text(
            text = genre.name,
            modifier = Modifier.padding(16.dp)
        )
    }
}
