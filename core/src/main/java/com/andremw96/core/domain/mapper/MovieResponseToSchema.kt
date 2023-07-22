package com.andremw96.core.domain.mapper

import com.andremw96.core.data.remote.response.MovieListByGenreResponse
import com.andremw96.core.domain.schema.Movie

interface MovieResponseToSchema {
    operator fun invoke(movieResponse: List<MovieListByGenreResponse.Result>): List<Movie>
}
