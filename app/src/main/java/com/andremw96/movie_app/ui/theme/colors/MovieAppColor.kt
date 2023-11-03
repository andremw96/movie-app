package com.andremw96.movie_app.ui.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf

class MovieAppColor(
    val errorPageColor: ErrorPageColor,
    val emptyPageColor: EmptyPageColor,
    val movieAppBarColor: MovieAppBarColor,
    val youtubePlayerViewColor: YoutubePlayerViewColor,
)

val LocalMovieAppColor = staticCompositionLocalOf {
    movieAppColorLight
}

val movieAppColorDark = MovieAppColor(
    errorPageColor = errorPageDark,
    emptyPageColor = emptyPageDark,
    movieAppBarColor = movieAppBarDark,
    youtubePlayerViewColor = youtubePlayerViewDark,
)

val movieAppColorLight = MovieAppColor(
    errorPageColor = errorPageLight,
    emptyPageColor = emptyPageLight,
    movieAppBarColor = movieAppBarLight,
    youtubePlayerViewColor = youtubePlayerViewLight,
)