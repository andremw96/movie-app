package com.andremw96.core.domain.repository

import com.andremw96.core.data.Resource
import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.remotedatasource.GenreRemoteDataSource
import com.andremw96.core.data.remote.remotedatasource.MovieByGenreDataSource
import com.andremw96.core.domain.mapper.GenreListResponseToSchema
import com.andremw96.core.domain.mapper.MovieResponseToSchemaImpl
import com.andremw96.core.domain.schema.Genre
import com.andremw96.core.domain.schema.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    private val movieByGenreDataSource: MovieByGenreDataSource,
    private val genreListResponseToSchema: GenreListResponseToSchema,
    private val movieResponseToSchemaImpl: MovieResponseToSchemaImpl,
) : MovieRepository {
    override fun getGenreList(): Flow<Resource<List<Genre>>> {
        return flow {
            emit(Resource.Loading())
            genreRemoteDataSource.getGenreList().collect {
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

    override fun getMovieListByGenreId(genreId: String): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            movieByGenreDataSource.getMovieListByGenreId(genreId).collect {
                when (it) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(movieResponseToSchemaImpl(it.data.results)))
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
