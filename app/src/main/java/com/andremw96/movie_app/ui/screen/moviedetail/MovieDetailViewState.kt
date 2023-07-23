package com.andremw96.movie_app.ui.screen.moviedetail

import com.andremw96.core.domain.schema.MovieDetail
import com.andremw96.core.domain.schema.MovieReview

data class MovieDetailViewState(
    val movieDetail: MovieDetail?,
    val isLoading: Boolean,
    val errorMessage: String?,
    val isLoadingUserReviews: Boolean,
    val errorMessageUserReviews: String?,
    val userReviews: List<MovieReview.Result>
) {
    companion object {
        fun initialState(): MovieDetailViewState =
            MovieDetailViewState(
                movieDetail = null,
                isLoading = false,
                errorMessage = null,
                isLoadingUserReviews = false,
                errorMessageUserReviews = null,
                userReviews = emptyList(),
            )
    }
}
