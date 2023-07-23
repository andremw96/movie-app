package com.andremw96.core.domain.usecase.impl

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.schema.MovieReview
import com.andremw96.core.domain.usecase.GetMovieReviewListByMovieId
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieReviewListByMovieIdImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetMovieReviewListByMovieId {
    override fun invoke(movieId: String, page: Int): Flow<Resource<Triple<List<MovieReview.Result>, Int, Int>>> {
        return movieRepository.getMovieReviewList(movieId, page)
    }
}
