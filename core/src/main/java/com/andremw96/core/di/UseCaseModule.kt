package com.andremw96.core.di

import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.usecase.GetGenre
import com.andremw96.core.domain.usecase.GetGenreImpl
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
    fun provideGetGenre(
        movieRepository: MovieRepository,
    ): GetGenre = GetGenreImpl(movieRepository = movieRepository)
}
