package com.andremw96.movie_app.ui.genrelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.andremw96.movie_app.R


@Composable
fun GenreListScreen(
    viewState: GenreListViewState,
    callbacks: GenreListCallbacks,
) {
    Scaffold(
        topBar = {
            GenreListAppBar()
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
                        GenreListErrorPage(
                            errorMessage = viewState.errorMessage,
                            callbacks = callbacks,
                        )
                    }
                    viewState.genreList.isEmpty() -> {
                        GenreListEmptyPage(callbacks = callbacks)
                    }
                    else -> {
                        LazyRow {
                            items(viewState.genreList.size) {
                                Text(
                                    viewState.genreList[it].name
                                )

                                Divider(
                                    color = Color.LightGray,
                                    thickness = 2.dp
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
fun GenreListAppBar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.genre_list_title),
                color = Color.Gray,
            )
        }

        Divider(
            color = Color.LightGray,
            thickness = 2.dp
        )
    }
}

@Composable
fun GenreListErrorPage(
    errorMessage: String,
    modifier: Modifier = Modifier,
    callbacks: GenreListCallbacks,
) {
    Column(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                id = R.string.something_went_wrong
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            text = errorMessage,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Button(onClick = {
            callbacks.loadGenreList()
        }) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun GenreListEmptyPage(
    modifier: Modifier = Modifier,
    callbacks: GenreListCallbacks?,
) {
    Column(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                id = R.string.something_went_wrong
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            text = stringResource(
                id = R.string.data_not_found
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(12.dp))

        if (callbacks != null) {
            Button(onClick = {
                callbacks.loadGenreList()
            }) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}
