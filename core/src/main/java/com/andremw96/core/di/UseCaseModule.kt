package com.andremw96.core.di

import com.andremw96.core.domain.mapper.GenreListResponseToSchema
import com.andremw96.core.domain.mapper.impl.GenreListResponseToSchemaImpl
import com.andremw96.core.domain.repository.MovieRepository
import com.andremw96.core.domain.usecase.GetGenreList
import com.andremw96.core.domain.usecase.impl.GetGenreListImpl
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
    ): GetGenreList = GetGenreListImpl(movieRepository = movieRepository)

    @Provides
    @Singleton
    fun provideGenreListResponseToSchema(): GenreListResponseToSchema =
        GenreListResponseToSchemaImpl()
}
