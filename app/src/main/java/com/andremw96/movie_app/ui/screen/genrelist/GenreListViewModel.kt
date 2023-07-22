package com.andremw96.movie_app.ui.screen.genrelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremw96.core.data.Resource
import com.andremw96.core.di.IoDispatcher
import com.andremw96.core.domain.usecase.GetGenreList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Collections.emptyList
import javax.inject.Inject

@HiltViewModel
class GenreListViewModel @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val getGenreList: GetGenreList,
) : ViewModel(), GenreListCallbacks {
    private val _viewState: MutableStateFlow<GenreListViewState> = MutableStateFlow(
        GenreListViewState.initialState()
    )
    val viewState = _viewState.asStateFlow()

    override fun loadGenreList() {
        viewModelScope.launch(coroutineDispatcher) {
            val genreList = getGenreList()
            genreList.collect {
                when (it) {
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            genreList = emptyList(),
                            isLoading = true,
                            errorMessage = null,
                        )
                    }
                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            genreList = emptyList(),
                            isLoading = false,
                            errorMessage = it.message,
                        )
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            genreList = it.data ?: emptyList(),
                            isLoading = false,
                            errorMessage = null,
                        )
                    }
                }
            }
        }
    }
}
