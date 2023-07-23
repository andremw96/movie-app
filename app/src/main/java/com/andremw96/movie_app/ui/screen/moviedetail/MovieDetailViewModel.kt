package com.andremw96.movie_app.ui.screen.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremw96.core.data.Resource
import com.andremw96.core.di.IoDispatcher
import com.andremw96.core.domain.usecase.GetMovieDetailByMovieId
import com.andremw96.core.domain.usecase.GetMovieReviewListByMovieId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val getMovieDetailByMovieId: GetMovieDetailByMovieId,
    private val getMovieReviewListByMovieId: GetMovieReviewListByMovieId,
) : ViewModel(), MovieDetailCallbacks {
    private val _viewState: MutableStateFlow<MovieDetailViewState> = MutableStateFlow(
        MovieDetailViewState.initialState()
    )

    val viewState = _viewState.asStateFlow()

    override fun loadMovieDetailByMovieId(movieId: String) {
        viewModelScope.launch(coroutineDispatcher) {
            getMovieDetailByMovieId(movieId = movieId).collect {
                when (it) {
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            movieDetail = null,
                            isLoading = true,
                            errorMessage = null,
                        )
                    }
                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            movieDetail = null,
                            isLoading = false,
                            errorMessage = it.message,
                        )
                    }
                    is Resource.Success -> {
                        loadMovieReviewListByMovieId(movieId)

                        _viewState.value = _viewState.value.copy(
                            movieDetail = it.data,
                            isLoading = false,
                            errorMessage = null,
                        )
                    }
                }
            }
        }
    }

    override fun loadMovieReviewListByMovieId(movieId: String) {
        viewModelScope.launch(coroutineDispatcher) {
            getMovieReviewListByMovieId(movieId = movieId).collect {
                when (it) {
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            isLoadingUserReviews = true,
                            errorMessageUserReviews = null,
                        )
                    }
                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            isLoadingUserReviews = false,
                            errorMessageUserReviews = it.message,
                        )
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            isLoadingUserReviews = false,
                            errorMessageUserReviews = null,
                            userReviews = it.data?.first ?: emptyList()
                        )
                    }
                }
            }
        }
    }
}
