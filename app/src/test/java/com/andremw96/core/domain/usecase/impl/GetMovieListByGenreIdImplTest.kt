package com.andremw96.core.domain.usecase.impl

import com.andremw96.core.domain.repository.MovieRepository
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runTest
import net.bytebuddy.utility.RandomString
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.random.Random


class GetMovieListByGenreIdImplTest {
    @Mock
    private lateinit var mockedMovieRepository: MovieRepository

    private lateinit var getMovieListByGenreIdImpl: GetMovieListByGenreIdImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getMovieListByGenreIdImpl = GetMovieListByGenreIdImpl(mockedMovieRepository)
    }

    @Test
    fun `invoke should call movierepository getMovieListByGenreId`() {
        runTest {
            val dummyGenreId = RandomString.make(2)
            val dummyPage = Random.nextInt()
            getMovieListByGenreIdImpl(dummyGenreId, dummyPage)

            verify(mockedMovieRepository).getMovieListByGenreId(eq(dummyGenreId), eq(dummyPage))
        }
    }
}
