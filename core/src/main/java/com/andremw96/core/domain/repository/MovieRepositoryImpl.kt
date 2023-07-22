package com.andremw96.core.domain.repository

import com.andremw96.core.data.Resource
import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.remotedatasource.GenreRemoteDataSource
import com.andremw96.core.domain.mapper.GenreListResponseToSchema
import com.andremw96.core.domain.schema.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    private val genreListResponseToSchema: GenreListResponseToSchema,
) : MovieRepository {
    override fun getGenreList(): Flow<Resource<List<Genre>>> {
        return flow {
            emit(Resource.Loading())
            val apiResponse = genreRemoteDataSource.getGenreList()
            apiResponse.collect {
                when (it) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(genreListResponseToSchema(it.data)))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Success(emptyList()))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(it.errorMessage))
                    }
                }
            }
        }
    }
}
