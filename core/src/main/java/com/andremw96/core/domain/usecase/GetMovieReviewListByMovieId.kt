package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.schema.MovieReview
import kotlinx.coroutines.flow.Flow


interface GetMovieReviewListByMovieId {
    operator fun invoke(movieId: String): Flow<Resource<Triple<List<MovieReview.Result>, Int, Int>>>
}
