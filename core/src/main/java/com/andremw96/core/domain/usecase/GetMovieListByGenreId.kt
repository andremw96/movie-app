package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.schema.Movie
import kotlinx.coroutines.flow.Flow


interface GetMovieListByGenreId {
    operator fun invoke(genreId: String,): Flow<Resource<List<Movie>>>
}
