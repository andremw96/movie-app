package com.andremw96.core.domain.repository

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.schema.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getGenreList(): Flow<Resource<List<Genre>>>
    fun getMovieListByGenreId(
        genreId: String,
        page: Int,
    ): Flow<Resource<Triple<List<Movie>, Int, Int>>>
    fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>>
    fun getMovieReviewList(
        movieId: String,
        page: Int
    ): Flow<Resource<Triple<List<MovieReview.Result>, Int, Int>>>
    fun getMovieTrailerList(movieId: String): Flow<Resource<List<MovieTrailer>>>
}
