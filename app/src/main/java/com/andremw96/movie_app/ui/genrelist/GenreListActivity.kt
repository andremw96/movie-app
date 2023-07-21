package com.andremw96.movie_app.ui.genrelist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.andremw96.movie_app.ui.theme.MovieAppCurrencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreListActivity : AppCompatActivity() {

    private val viewModel: GenreListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppCurrencyTheme {
                val viewState by viewModel.viewState.collectAsState()

                if (savedInstanceState == null) {
                    LaunchedEffect(key1 = Unit, block = {
                        viewModel.loadGenreList()
                    })
                }

                GenreListScreen(
                    viewState = viewState,
                    callbacks = viewModel
                )
            }
        }
    }
}
