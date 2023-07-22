package com.andremw96.movie_app.ui.screen.movielistbygenre

import com.andremw96.core.domain.schema.Movie

data class MovieListByGenreViewState(
    val movieList: List<Movie>,
    val isLoading: Boolean,
    val errorMessage: String?,
) {
    companion object {
        fun initialState(): MovieListByGenreViewState =
            MovieListByGenreViewState(
                movieList = emptyList(),
                isLoading = false,
                errorMessage = null
            )
    }
}
