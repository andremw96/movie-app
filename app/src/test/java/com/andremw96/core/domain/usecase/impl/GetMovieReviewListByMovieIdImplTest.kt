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

class GetMovieReviewListByMovieIdImplTest {
    @Mock
    private lateinit var mockedMovieRepository: MovieRepository

    private lateinit var getMovieReviewListByMovieIdImpl: GetMovieReviewListByMovieIdImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getMovieReviewListByMovieIdImpl = GetMovieReviewListByMovieIdImpl(mockedMovieRepository)
    }

    @Test
    fun `invoke should call repository getMovieReviewList`() {
        runTest {
            val dummyMovieId = RandomString.make(2)
            val dummyPage = Random.nextInt()

            getMovieReviewListByMovieIdImpl.invoke(dummyMovieId, dummyPage)

            verify(mockedMovieRepository).getMovieReviewList(eq(dummyMovieId), eq(dummyPage))
        }
    }
}
