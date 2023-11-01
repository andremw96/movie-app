package com.andremw96.movie_app.ui.theme

import androidx.compose.ui.graphics.Color

class ErrorPageColor(
    val backgroundColor: Color,
)

val errorPageLight = ErrorPageColor(
    backgroundColor = MyAppColors.background,
)

val errorPageDark = ErrorPageColor(
    backgroundColor = MyAppColors.backgroundDark,
)