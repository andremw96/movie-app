package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.schema.Genre
import kotlinx.coroutines.flow.Flow

class GetGenreImpl(
    private val movieRepository: MovieRepository,
) : GetGenre {
    override fun invoke(): Flow<Resource<List<Genre>>> {
        return movieRepository.getGenreList()
    }
}
