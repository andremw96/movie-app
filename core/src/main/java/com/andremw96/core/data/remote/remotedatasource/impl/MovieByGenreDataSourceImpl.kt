package com.andremw96.core.data.remote.remotedatasource.impl

import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.network.MovieDbApi
import com.andremw96.core.data.remote.remotedatasource.MovieByGenreDataSource
import com.andremw96.core.data.remote.response.MovieDetailResponse
import com.andremw96.core.data.remote.response.MovieListByGenreResponse
import com.andremw96.core.data.remote.response.MovieReviewListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieByGenreDataSourceImpl @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val movieDbApi: MovieDbApi,
) : MovieByGenreDataSource {
    override fun getMovieListByGenreId(
        genreId: String,
        page: Int
    ): Flow<ApiResponse<MovieListByGenreResponse>> {
        return flow {
            try {
                val movieListByGenreId =
                    movieDbApi.getMovieListByGenreId(withGenres = genreId, page = page)

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

    override fun getMovieDetailByMovieId(movieId: String): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val movieDetailByMovieId = movieDbApi.getMovieDetailById(movieId = movieId)

                emit(ApiResponse.Success(movieDetailByMovieId))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(coroutineDispatcher)
    }

    override fun getMovieReviewListByMovieId(movieId: String, page: Int): Flow<ApiResponse<MovieReviewListResponse>> {
        return flow {
            try {
                val movieReviewList =
                    movieDbApi.getMovieReviewListById(movieId = movieId, page = page)

                if (movieReviewList.results.isNotEmpty()) {
                    emit(ApiResponse.Success(movieReviewList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(coroutineDispatcher)
    }
}
