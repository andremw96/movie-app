package com.andremw96.movie_app.ui.theme.colors

import androidx.compose.ui.graphics.Color

class ErrorPageColor(
    val backgroundColor: Color,
    val textColor: Color,
)

val errorPageLight = ErrorPageColor(
    backgroundColor = MyAppColors.white,
    textColor = MyAppColors.black
)

val errorPageDark = ErrorPageColor(
    backgroundColor = MyAppColors.gray,
    textColor = MyAppColors.white,
)