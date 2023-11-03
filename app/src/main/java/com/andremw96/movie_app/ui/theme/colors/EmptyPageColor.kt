package com.andremw96.movie_app.ui.theme.colors

import androidx.compose.ui.graphics.Color

class EmptyPageColor(
    val backgroundColor: Color,
    val textColor: Color,
)

val emptyPageLight = EmptyPageColor(
    backgroundColor = MyAppColors.white,
    textColor = MyAppColors.black
)

val emptyPageDark = EmptyPageColor(
    backgroundColor = MyAppColors.gray,
    textColor = MyAppColors.white,
)