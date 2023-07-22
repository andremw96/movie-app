package com.andremw96.core.domain.mapper

import com.andremw96.core.data.remote.response.GenreListResponse
import com.andremw96.core.domain.schema.Genre

interface GenreListResponseToSchema {
    operator fun invoke(genreListResponse: GenreListResponse): List<Genre>
}
