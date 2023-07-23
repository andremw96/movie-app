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


class GetMovieDetailByMovieIdImplTest {
    @Mock
    private lateinit var mockedMovieRepository: MovieRepository

    private lateinit var getMovieDetailByMovieIdImpl: GetMovieDetailByMovieIdImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getMovieDetailByMovieIdImpl = GetMovieDetailByMovieIdImpl(mockedMovieRepository)
    }

    @Test
    fun `invoke should call movierepository getMovieDetail`() {
        runTest {
            val dummyMovieId = RandomString.make(2)
            getMovieDetailByMovieIdImpl.invoke(dummyMovieId)

            verify(mockedMovieRepository).getMovieDetail(eq(dummyMovieId))
        }
    }
}
