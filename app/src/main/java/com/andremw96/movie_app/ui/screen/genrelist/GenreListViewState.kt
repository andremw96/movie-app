package com.andremw96.movie_app.ui.screen.genrelist

import com.andremw96.core.domain.schema.Genre

data class GenreListViewState(
    val genreList: List<Genre>,
    val isLoading: Boolean,
    val errorMessage: String?,
) {
    companion object {
        fun initialState(): GenreListViewState =
            GenreListViewState(
                genreList = emptyList(),
                isLoading = false,
                errorMessage = null
            )
    }
}
