package com.andremw96.core.data.remote.remotedatasource

import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.response.GenreListResponse
import kotlinx.coroutines.flow.Flow

interface GenreRemoteDataSource {
    fun getGenreList(): Flow<ApiResponse<GenreListResponse>>
}
