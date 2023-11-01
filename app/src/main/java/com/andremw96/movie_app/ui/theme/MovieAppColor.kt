package com.andremw96.movie_app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf

class MovieAppColor(
    val errorPageColor: ErrorPageColor,
)

val LocalMovieAppColor = staticCompositionLocalOf {
    movieAppColorLight
}

val movieAppColorDark = MovieAppColor(
    errorPageColor = errorPageDark,
)

val movieAppColorLight = MovieAppColor(
    errorPageColor = errorPageLight,
)