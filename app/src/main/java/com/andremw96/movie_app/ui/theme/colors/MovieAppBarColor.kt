package com.andremw96.movie_app.ui.theme.colors

import androidx.compose.ui.graphics.Color

class MovieAppBarColor(
    val backgroundColor: Color,
    val textColor: Color,
    val backButtonColor: Color,
    val dividerColor: Color,
)

val movieAppBarLight = MovieAppBarColor(
    backgroundColor = MyAppColors.white,
    textColor = MyAppColors.black,
    backButtonColor = MyAppColors.gray,
    dividerColor = MyAppColors.lightGray,
)

val movieAppBarDark = MovieAppBarColor(
    backgroundColor = MyAppColors.gray,
    textColor = MyAppColors.white,
    backButtonColor = MyAppColors.white,
    dividerColor = MyAppColors.lightGray,
)