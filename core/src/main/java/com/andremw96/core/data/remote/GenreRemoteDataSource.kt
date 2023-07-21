package com.andremw96.core.data.remote

import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.response.GenreListResponse
import kotlinx.coroutines.flow.Flow

interface GenreRemoteDataSource {
    fun getCurrenciesWithLatestExchangeRate(): Flow<ApiResponse<GenreListResponse>>
}
