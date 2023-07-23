package com.andremw96.movie_app.ui.screen.movielistbygenre

import com.andremw96.core.domain.schema.Movie

data class MovieListByGenreViewState(
    val movieList: List<Movie>,
    val totalPages: Int,
    val totalResults: Int,
    val isLoading: Boolean,
    val errorMessage: String?,
    val currentPage: Int,
    val isLoadingMore: Boolean,
) {
    companion object {
        fun initialState(): MovieListByGenreViewState =
            MovieListByGenreViewState(
                movieList = emptyList(),
                totalPages = 0,
                totalResults = 0,
                isLoading = false,
                errorMessage = null,
                currentPage = 1,
                isLoadingMore = false,
            )
    }
}
