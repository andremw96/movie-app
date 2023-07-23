package com.andremw96.core.domain.usecase.impl

import com.andremw96.core.domain.repository.MovieRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GetGenreListImplTest {

    @Mock
    private lateinit var mockedMovieRepository: MovieRepository

    private lateinit var getGenreListImpl: GetGenreListImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getGenreListImpl = GetGenreListImpl(mockedMovieRepository)
    }

    @Test
    fun `invoke should call movierepository getGenreList`() {
        runTest {
            getGenreListImpl.invoke()

            verify(mockedMovieRepository).getGenreList()
        }
    }
}
