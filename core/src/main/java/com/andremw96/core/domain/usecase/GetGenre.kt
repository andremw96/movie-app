package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.schema.Genre
import kotlinx.coroutines.flow.Flow


interface GetGenre {
    operator fun invoke(): Flow<Resource<List<Genre>>>
}
