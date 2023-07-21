package com.andremw96.core.data.remote.remotedatasource.impl

import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.network.MovieDbApi
import com.andremw96.core.data.remote.remotedatasource.GenreRemoteDataSource
import com.andremw96.core.data.remote.response.GenreListResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreRemoteDataSourceImpl @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val movieDbApi: MovieDbApi,
) : GenreRemoteDataSource {
    override fun getCurrenciesWithLatestExchangeRate(): Flow<ApiResponse<GenreListResponse>> {
        return flow {
            try {
                val genres = movieDbApi.getGenreList()

                if (genres.genres.isNotEmpty()) {
                    emit(ApiResponse.Success(genres))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }

                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(coroutineDispatcher)
    }
}
