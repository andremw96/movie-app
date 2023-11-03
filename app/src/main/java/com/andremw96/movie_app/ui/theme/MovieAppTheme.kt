package com.andremw96.movie_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import com.andremw96.movie_app.ui.theme.colors.LocalMovieAppColor
import com.andremw96.movie_app.ui.theme.colors.MovieAppColor
import com.andremw96.movie_app.ui.theme.colors.movieAppColorDark
import com.andremw96.movie_app.ui.theme.colors.movieAppColorLight

object MovieAppTheme {
    val colors: MovieAppColor
        @Composable get() = LocalMovieAppColor.current
}

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        movieAppColorDark
    } else movieAppColorLight

    CompositionLocalProvider(LocalMovieAppColor provides colors) {
        MaterialTheme(
            shapes = Shapes,
            content = content
        )
    }
}

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)
