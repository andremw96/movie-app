package com.andremw96.core.data.remote.remotedatasource.impl

import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.network.MovieDbApi
import com.andremw96.core.data.remote.remotedatasource.MovieByGenreDataSource
import com.andremw96.core.data.remote.response.MovieListByGenreResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieByGenreDataSourceImpl @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val movieDbApi: MovieDbApi,
) : MovieByGenreDataSource {
    override fun getMovieListByGenreId(genreId: String): Flow<ApiResponse<MovieListByGenreResponse>> {
        return flow {
            try {
                val movieListByGenreId = movieDbApi.getMovieListByGenreId(withGenres = genreId)

                if (movieListByGenreId.results.isNotEmpty()) {
                    emit(ApiResponse.Success(movieListByGenreId))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(coroutineDispatcher)
    }
}
