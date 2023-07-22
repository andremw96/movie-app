package com.andremw96.core.domain.repository

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.schema.Genre
import com.andremw96.core.domain.schema.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getGenreList(): Flow<Resource<List<Genre>>>

    fun getMovieListByGenreId(genreId: String): Flow<Resource<List<Movie>>>
}
