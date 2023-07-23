package com.andremw96.core.domain.mapper

import com.andremw96.core.data.remote.response.MovieDetailResponse
import com.andremw96.core.data.remote.response.MovieListByGenreResponse
import com.andremw96.core.domain.schema.Movie
import com.andremw96.core.domain.schema.MovieDetail

interface MovieResponseToSchema {
    fun movieResponseToSchema(movieResponse: MovieListByGenreResponse): Triple<List<Movie>, Int, Int>

    fun movieResponseDetailToSchema(movieDetailResponse: MovieDetailResponse): MovieDetail
}
