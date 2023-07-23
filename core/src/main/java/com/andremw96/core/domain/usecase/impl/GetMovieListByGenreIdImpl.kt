package com.andremw96.core.domain.usecase.impl

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.schema.Movie
import com.andremw96.core.domain.usecase.GetMovieListByGenreId
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieListByGenreIdImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetMovieListByGenreId {
    override fun invoke(genreId: String, page: Int,): Flow<Resource<Triple<List<Movie>, Int, Int>>> {
        return movieRepository.getMovieListByGenreId(genreId = genreId, page = page)
    }
}
