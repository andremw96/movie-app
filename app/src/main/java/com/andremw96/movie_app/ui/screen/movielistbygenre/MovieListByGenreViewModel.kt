package com.andremw96.movie_app.ui.screen.movielistbygenre

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremw96.core.data.Resource
import com.andremw96.core.di.IoDispatcher
import com.andremw96.core.domain.usecase.GetMovieListByGenreId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListByGenreViewModel @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val getMovieListByGenreId: GetMovieListByGenreId,
) : ViewModel(), MovieListByGenreCallbacks {
    private val _viewState: MutableStateFlow<MovieListByGenreViewState> = MutableStateFlow(
        MovieListByGenreViewState.initialState()
    )

    val viewState = _viewState.asStateFlow()

    override fun loadMovieListByGenre(genreId: String) {
        viewModelScope.launch(coroutineDispatcher) {
            getMovieListByGenreId(genreId = genreId, page = 1).collect {
                when (it) {
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            movieList = emptyList(),
                            isLoading = true,
                            errorMessage = null,
                        )
                    }
                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            movieList = emptyList(),
                            isLoading = false,
                            errorMessage = it.message,
                        )
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            movieList = it.data?.first ?: emptyList(),
                            isLoading = false,
                            errorMessage = null,
                            totalPages = it.data?.second ?: 0,
                            totalResults = it.data?.third ?: 0,
                        )
                    }
                }
            }
        }
    }

    override fun loadMoreMovieListByGenre(genreId: String) {
        val newPageToLoad = _viewState.value.currentPage+1
        Log.d("loadmore", "$newPageToLoad")
        viewModelScope.launch(coroutineDispatcher) {
            getMovieListByGenreId(genreId = genreId, page = newPageToLoad).collect {
                when (it) {
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            errorMessage = null,
                            isLoadingMore = true,
                        )
                    }
                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            errorMessage = null,
                            isLoadingMore = false,
                        )
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            movieList = _viewState.value.movieList + (it.data?.first ?: emptyList()),
                            isLoading = false,
                            errorMessage = null,
                            totalPages = it.data?.second ?: 0,
                            totalResults = it.data?.third ?: 0,
                            isLoadingMore = false,
                            currentPage = newPageToLoad
                        )
                    }
                }
            }
        }
    }
}
