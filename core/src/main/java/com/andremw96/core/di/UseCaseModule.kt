package com.andremw96.core.di

import com.andremw96.core.domain.mapper.GenreListResponseToSchema
import com.andremw96.core.domain.mapper.MovieResponseToSchema
import com.andremw96.core.domain.mapper.MovieResponseToSchemaImpl
import com.andremw96.core.domain.mapper.impl.GenreListResponseToSchemaImpl
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.usecase.GetGenreList
import com.andremw96.core.domain.usecase.GetMovieDetailByMovieId
import com.andremw96.core.domain.usecase.GetMovieListByGenreId
import com.andremw96.core.domain.usecase.GetMovieReviewListByMovieId
import com.andremw96.core.domain.usecase.impl.GetGenreListImpl
import com.andremw96.core.domain.usecase.impl.GetMovieDetailByMovieIdImpl
import com.andremw96.core.domain.usecase.impl.GetMovieListByGenreIdImpl
import com.andremw96.core.domain.usecase.impl.GetMovieReviewListByMovieIdImpl
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
    fun provideGenreListResponseToSchema(): GenreListResponseToSchema =
        GenreListResponseToSchemaImpl()

    @Provides
    @Singleton
    fun provideMovieResponseToSchema(): MovieResponseToSchema =
        MovieResponseToSchemaImpl()
}
