package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.schema.MovieTrailer
import kotlinx.coroutines.flow.Flow


interface GetMovieTrailerListByMovieId {
    operator fun invoke(movieId: String): Flow<Resource<List<MovieTrailer>>>
}
