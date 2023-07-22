package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.schema.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieListByGenreIdImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetMovieListByGenreId {
    override fun invoke(genreId: String): Flow<Resource<List<Movie>>> {
        return movieRepository.getMovieListByGenreId(genreId = genreId)
    }
}