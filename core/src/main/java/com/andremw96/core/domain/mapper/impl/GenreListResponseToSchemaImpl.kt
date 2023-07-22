package com.andremw96.core.domain.mapper.impl

import com.andremw96.core.data.remote.response.GenreListResponse
import com.andremw96.core.domain.mapper.GenreListResponseToSchema
import com.andremw96.core.domain.schema.Genre
import javax.inject.Inject

class GenreListResponseToSchemaImpl @Inject constructor() : GenreListResponseToSchema {
    override fun invoke(genreListResponse: GenreListResponse): List<Genre> {
        return genreListResponse.genres.map {
            Genre(
                id = it.id,
                name = it.name,
            )
        }
    }
}
