package com.andremw96.movie_app.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andremw96.movie_app.R
import com.andremw96.movie_app.ui.theme.MovieAppTheme

@Composable
fun MovieEmptyPage(
    modifier: Modifier = Modifier,
    retryOnClick: (() -> Unit)?,
) {
    Column(
        modifier = modifier
            .background(color = MovieAppTheme.colors.emptyPageColor.backgroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                id = R.string.something_went_wrong
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            color = MovieAppTheme.colors.emptyPageColor.textColor,
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            text = stringResource(
                id = R.string.data_not_found
            ),
            textAlign = TextAlign.Center,
            color = MovieAppTheme.colors.emptyPageColor.textColor,
        )

        Spacer(modifier = Modifier.padding(12.dp))

        if (retryOnClick != null) {
            Button(onClick = {
                retryOnClick()
            }) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}

@Preview
@Composable
fun MovieEmptyPagePreview() {
    MovieAppTheme(darkTheme = true) {
        MovieEmptyPage {

        }
    }
}
