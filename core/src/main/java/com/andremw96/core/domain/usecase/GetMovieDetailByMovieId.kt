package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.schema.MovieDetail
import kotlinx.coroutines.flow.Flow


interface GetMovieDetailByMovieId {
    operator fun invoke(movieId: String): Flow<Resource<MovieDetail>>
}
