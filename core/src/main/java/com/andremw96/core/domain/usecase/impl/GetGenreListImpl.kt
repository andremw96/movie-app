package com.andremw96.core.domain.usecase.impl

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.schema.Genre
import com.andremw96.core.domain.usecase.GetGenreList
import kotlinx.coroutines.flow.Flow

class GetGenreListImpl(
    private val movieRepository: MovieRepository,
) : GetGenreList {
    override fun invoke(): Flow<Resource<List<Genre>>> {
        return movieRepository.getGenreList()
    }
}
