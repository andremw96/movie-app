package com.andremw96.core.domain.usecase.impl

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.schema.MovieDetail
import com.andremw96.core.domain.usecase.GetMovieDetailByMovieId
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailByMovieIdImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetMovieDetailByMovieId {
    override fun invoke(movieId: String): Flow<Resource<MovieDetail>> {
        return movieRepository.getMovieDetail(movieId)
    }
}
