package com.andremw96.core.di

import com.andremw96.core.domain.mapper.GenreListResponseToSchema
import com.andremw96.core.domain.mapper.MovieResponseToSchema
import com.andremw96.core.domain.mapper.MovieResponseToSchemaImpl
import com.andremw96.core.domain.mapper.impl.GenreListResponseToSchemaImpl
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.usecase.*
import com.andremw96.core.domain.usecase.impl.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetGenreList(
        movieRepository: MovieRepository,
    ): GetGenreList = GetGenreListImpl(movieRepository = movieRepository)

    @Provides
    @Singleton
    fun provideGetMovieListByGenreId(
        movieRepository: MovieRepository,
    ): GetMovieListByGenreId = GetMovieListByGenreIdImpl(movieRepository = movieRepository)

    @Provides
    @Singleton
    fun provideGetMovieDetailByMovieId(
        movieRepository: MovieRepository,
    ): GetMovieDetailByMovieId = GetMovieDetailByMovieIdImpl(movieRepository = movieRepository)

    @Provides
    @Singleton
    fun provideGetMovieReviewListByMovieId(
        movieRepository: MovieRepository,
    ): GetMovieReviewListByMovieId =
        GetMovieReviewListByMovieIdImpl(movieRepository = movieRepository)

    @Provides
    @Singleton
    fun provideGetMovieTrailerListByMovieId(
        movieRepository: MovieRepository,
    ): GetMovieTrailerListByMovieId =
        GetMovieTrailerListByMovieIdImpl(movieRepository = movieRepository)

    @Provides
    @Singleton
    fun provideGenreListResponseToSchema(): GenreListResponseToSchema =
        GenreListResponseToSchemaImpl()

    @Provides
    @Singleton
    fun provideMovieResponseToSchema(): MovieResponseToSchema =
        MovieResponseToSchemaImpl()
}
