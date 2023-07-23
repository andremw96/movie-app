package com.andremw96.movie_app.ui.screen.moviedetail

interface MovieDetailCallbacks {
    fun loadMovieDetailByMovieId(movieId: String)
    fun loadMovieReviewListByMovieId(movieId: String)
    fun loadMoreMovieReviewListByMovieId(movieId: String)
    fun loadMovieTrailerListByMovieId(movieId: String)
}
