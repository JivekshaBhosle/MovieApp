package com.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.domain.model.response.BaseMovieResponse
import com.movieapp.domain.repository.IRepositoryMovies
import com.movieapp.domain.usecase.UseCaseMovieLinksGet
import com.movieapp.presentation.viewmodel.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelMovie(
    private val repository: IRepositoryMovies
) : ViewModel() {

    private val moviesMutableStateFlow: MutableStateFlow<UIState> =
        MutableStateFlow(UIState.LoadingUIState)
    val moviesFlow: StateFlow<UIState> = moviesMutableStateFlow

    private var useCaseMovies: UseCaseMovieLinksGet? = null

    fun getMovies() {
        moviesMutableStateFlow.value = UIState.LoadingUIState

        viewModelScope.launch {
            useCaseMovies =
                UseCaseMovieLinksGet(repository = repository) { response: BaseMovieResponse ->

                    if (response.responseCode.statusCode == "200"
                        && response.responseCode.httpStatus == "200"
                        && response.errorResponse.message.isBlank()
                    ) {
                        val movieLinks = response.links
                        moviesMutableStateFlow.value =
                            if (movieLinks.sections.isNotEmpty())
                                UIState.ListResultsUIState(movieLinks.sections)
                            else UIState.EmptyUIState
                    } else {
                        moviesMutableStateFlow.value = UIState.ErrorUIState
                    }
                }.also { it.execute() }
        }
    }

    fun onViewDestroyed() {
        useCaseMovies?.cancel()
    }
}