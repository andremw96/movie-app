package com.andremw96.core.domain.repository

import com.andremw96.core.data.NetworkBoundResource
import com.andremw96.core.data.Resource
import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.remotedatasource.GenreRemoteDataSource
import com.andremw96.core.data.remote.response.GenreListResponse
import com.andremw96.core.domain.schema.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
) : MovieRepository {
    override fun getGenreList(): Flow<Resource<List<Genre>>> =
        object : NetworkBoundResource<List<Genre>, GenreListResponse>() {
            override fun loadFromDB(): Flow<List<Genre>> {
                return flowOf()
            }

            override fun createCall(): Flow<ApiResponse<GenreListResponse>> {
                return genreRemoteDataSource.getGenreList()
            }

            override suspend fun saveCallResult(data: GenreListResponse) {
                // not yet implement the local data
            }

            override fun shouldFetch(data: List<Genre>?): Boolean {
                return true
            }
        }.asFlow()
}
