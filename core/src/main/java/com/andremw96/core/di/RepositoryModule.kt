package com.andremw96.core.di

import com.andremw96.core.data.remote.remotedatasource.GenreRemoteDataSource
import com.andremw96.core.data.remote.remotedatasource.MovieByGenreDataSource
import com.andremw96.core.domain.mapper.GenreListResponseToSchema
import com.andremw96.core.domain.mapper.MovieResponseToSchema
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(
        genreRemoteDataSource: GenreRemoteDataSource,
        movieByGenreDataSource: MovieByGenreDataSource,
        genreListResponseToSchema: GenreListResponseToSchema,
        movieResponseToSchema: MovieResponseToSchema,
    ): MovieRepository = MovieRepositoryImpl(
        genreRemoteDataSource = genreRemoteDataSource,
        movieByGenreDataSource = movieByGenreDataSource,
        genreListResponseToSchema = genreListResponseToSchema,
        movieResponseToSchema = movieResponseToSchema,
    )
}
