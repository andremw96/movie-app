package com.andremw96.core.domain.mapper

import com.andremw96.core.data.remote.response.MovieDetailResponse
import com.andremw96.core.data.remote.response.MovieListByGenreResponse
import com.andremw96.core.data.remote.response.MovieReviewListResponse
import com.andremw96.core.data.remote.response.MovieTrailerListResponse
import com.andremw96.core.domain.schema.Movie
import com.andremw96.core.domain.schema.MovieDetail
import com.andremw96.core.domain.schema.MovieReview
import com.andremw96.core.domain.schema.MovieTrailer

interface MovieResponseToSchema {
    fun movieResponseToSchema(movieResponse: MovieListByGenreResponse): Triple<List<Movie>, Int, Int>
    fun movieResponseDetailToSchema(movieDetailResponse: MovieDetailResponse): MovieDetail
    fun movieReviewListToSchema(movieReviewListResponse: MovieReviewListResponse): Triple<List<MovieReview.Result>, Int, Int>
    fun movieTrailerListToSchema(movieTrailerListResponse: MovieTrailerListResponse): List<MovieTrailer>
}
