package com.andremw96.movie_app.ui.screen.moviedetail

import com.andremw96.core.domain.schema.MovieDetail

data class MovieDetailViewState(
    val movieDetail: MovieDetail?,
    val isLoading: Boolean,
    val errorMessage: String?,
) {
    companion object {
        fun initialState(): MovieDetailViewState =
            MovieDetailViewState(
                movieDetail = null,
                isLoading = false,
                errorMessage = null
            )
    }
}
