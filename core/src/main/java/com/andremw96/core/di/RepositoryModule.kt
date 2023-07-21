package com.andremw96.core.di

import com.andremw96.core.data.remote.remotedatasource.GenreRemoteDataSource
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
    ): MovieRepository = MovieRepositoryImpl(genreRemoteDataSource = genreRemoteDataSource)
}