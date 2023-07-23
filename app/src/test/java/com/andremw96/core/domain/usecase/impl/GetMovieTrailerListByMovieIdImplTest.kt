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


class GetMovieTrailerListByMovieIdImplTest {
    @Mock
    private lateinit var mockedMovieRepository: MovieRepository

    private lateinit var getMovieTrailerListByMovieIdImpl: GetMovieTrailerListByMovieIdImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getMovieTrailerListByMovieIdImpl = GetMovieTrailerListByMovieIdImpl(
            mockedMovieRepository
        )
    }

    @Test
    fun `invoke should call movierepository getMovieTrailerList`() {
        runTest {
            val dummyMovieId = RandomString.make(2)

            getMovieTrailerListByMovieIdImpl.invoke(dummyMovieId)

            verify(mockedMovieRepository).getMovieTrailerList(eq(dummyMovieId))
        }
    }
}
