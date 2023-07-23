package com.andremw96.core.domain.mapper

import com.andremw96.core.data.remote.response.MovieDetailResponse
import com.andremw96.core.data.remote.response.MovieListByGenreResponse
import com.andremw96.core.data.remote.response.MovieReviewListResponse
import com.andremw96.core.domain.schema.Movie
import com.andremw96.core.domain.schema.MovieDetail
import com.andremw96.core.domain.schema.MovieReview
import javax.inject.Inject

class MovieResponseToSchemaImpl @Inject constructor() : MovieResponseToSchema {
    override fun movieResponseToSchema(movieResponse: MovieListByGenreResponse): Triple<List<Movie>, Int, Int> {
        return Triple(
            movieResponse.results.map {
                Movie(
                    adult = it.adult,
                    backdropPath = it.backdropPath ?: "",
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
            },
            movieResponse.totalPages,
            movieResponse.totalResults,
        )
    }

    override fun movieResponseDetailToSchema(movieDetailResponse: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            adult = movieDetailResponse.adult,
            backdropPath = movieDetailResponse.backdropPath ?: "",
            belongsToCollection = MovieDetail.BelongsToCollection(
                backdropPath = movieDetailResponse.belongsToCollection?.backdropPath ?: "",
                id = movieDetailResponse.belongsToCollection?.id ?: -1,
                name = movieDetailResponse.belongsToCollection?.name ?: "",
                posterPath = movieDetailResponse.belongsToCollection?.posterPath ?: "",
            ),
            budget = movieDetailResponse.budget,
            genres = movieDetailResponse.genres.map {
                MovieDetail.Genre(
                    id = it.id,
                    name = it.name
                )
            },
            homepage = movieDetailResponse.homepage,
            id = movieDetailResponse.id,
            imdbId = movieDetailResponse.imdbId,
            originalLanguage = movieDetailResponse.originalLanguage,
            originalTitle = movieDetailResponse.originalTitle,
            overview = movieDetailResponse.overview,
            popularity = movieDetailResponse.popularity,
            posterPath = movieDetailResponse.posterPath,
            productionCompanies = movieDetailResponse.productionCompanies.map {
                MovieDetail.ProductionCompany(
                    id = it?.id ?: -1,
                    logoPath = it?.logoPath ?: "",
                    name = it?.name ?: "",
                    originCountry = it?.originCountry ?: "",
                )
            },
            productionCountries = movieDetailResponse.productionCountries.map {
                MovieDetail.ProductionCountry(
                    iso31661 = it?.iso31661 ?: "",
                    name = it?.name ?: ""
                )
            },
            releaseDate = movieDetailResponse.releaseDate,
            revenue = movieDetailResponse.revenue,
            runtime = movieDetailResponse.runtime,
            spokenLanguages = movieDetailResponse.spokenLanguages.map {
                MovieDetail.SpokenLanguage(
                    englishName = it.englishName,
                    iso6391 = it.iso6391,
                    name = it.name
                )
            },
            status = movieDetailResponse.status,
            tagline = movieDetailResponse.tagline,
            title = movieDetailResponse.title,
            video = movieDetailResponse.video,
            voteAverage = movieDetailResponse.voteAverage,
            voteCount = movieDetailResponse.voteCount,
        )
    }

    override fun movieReviewListToSchema(movieReviewListResponse: MovieReviewListResponse): Triple<List<MovieReview.Result>, Int, Int> {
        return Triple(
            movieReviewListResponse.results.map {
                MovieReview.Result(
                    author = it.author,
                    authorDetails = MovieReview.Result.AuthorDetails(
                        avatarPath = it.authorDetails.avatarPath,
                        name = it.authorDetails.name,
                        rating = it.authorDetails.rating,
                        username = it.authorDetails.username,
                    ),
                    content = it.content,
                    createdAt = it.createdAt,
                    id = it.id,
                    updatedAt = it.updatedAt,
                    url = it.url
                )
            },
            movieReviewListResponse.totalPages,
            movieReviewListResponse.totalResults
        )
    }
}
