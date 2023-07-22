package com.andremw96.core.data.remote.remotedatasource

import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.response.MovieListByGenreResponse
import kotlinx.coroutines.flow.Flow

interface MovieByGenreDataSource {
    fun getMovieListByGenreId(genreId: String): Flow<ApiResponse<MovieListByGenreResponse>>
}
