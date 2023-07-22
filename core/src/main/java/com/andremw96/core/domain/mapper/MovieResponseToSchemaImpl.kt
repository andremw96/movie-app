package com.andremw96.core.domain.mapper

import com.andremw96.core.data.remote.response.MovieListByGenreResponse
import com.andremw96.core.domain.schema.Movie
import javax.inject.Inject

class MovieResponseToSchemaImpl @Inject constructor() : MovieResponseToSchema {
    override fun invoke(movieResponse: List<MovieListByGenreResponse.Result>): List<Movie> {
        return movieResponse.map {
            Movie(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                id = it.id,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }
    }
}
