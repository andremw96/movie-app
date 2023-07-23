package com.andremw96.core.domain.usecase.impl

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.schema.MovieTrailer
import com.andremw96.core.domain.usecase.GetMovieTrailerListByMovieId
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieTrailerListByMovieIdImpl @Inject constructor(
    private val movieRepository: MovieRepository,
): GetMovieTrailerListByMovieId {
    override fun invoke(movieId: String): Flow<Resource<List<MovieTrailer>>> {
        return movieRepository.getMovieTrailerList(movieId)
    }
}
