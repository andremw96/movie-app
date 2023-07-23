package com.andremw96.core.domain.repository

import com.andremw96.core.data.Resource
import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.remotedatasource.GenreRemoteDataSource
import com.andremw96.core.data.remote.remotedatasource.MovieByGenreDataSource
import com.andremw96.core.domain.mapper.GenreListResponseToSchema
import com.andremw96.core.domain.mapper.MovieResponseToSchema
import com.andremw96.core.domain.schema.Genre
import com.andremw96.core.domain.schema.Movie
import com.andremw96.core.domain.schema.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    private val movieByGenreDataSource: MovieByGenreDataSource,
    private val genreListResponseToSchema: GenreListResponseToSchema,
    private val movieResponseToSchema: MovieResponseToSchema,
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

    override fun getMovieListByGenreId(
        genreId: String,
        page: Int,
    ): Flow<Resource<Triple<List<Movie>, Int, Int>>> {
        return flow {
            emit(Resource.Loading())
            movieByGenreDataSource.getMovieListByGenreId(genreId, page).collect {
                when (it) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(movieResponseToSchema.movieResponseToSchema(it.data)))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Success(Triple(emptyList(), 0, 0)))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(it.errorMessage))
                    }
                }
            }
        }
    }

    override fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>> {
        return flow {
            emit(Resource.Loading())
            movieByGenreDataSource.getMovieDetailByMovieId(movieId).collect {
                when (it) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(movieResponseToSchema.movieResponseDetailToSchema(it.data)))
                    }
                    is ApiResponse.Empty -> {
                        // currently there is no empty state
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(it.errorMessage))
                    }
                }
            }
        }
    }
}
