package com.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.domain.model.MovieDetail
import com.movieapp.domain.repository.IRepositoryMovies
import com.movieapp.domain.usecase.UseCaseMovieDetailsGet
import com.movieapp.presentation.viewmodel.state.UIDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelDetail(
    private val repository: IRepositoryMovies
) : ViewModel() {

    private val moviesMutableStateFlow: MutableStateFlow<UIDetailState> =
        MutableStateFlow(UIDetailState.LoadingUIState)
    val moviesFlow: StateFlow<UIDetailState> = moviesMutableStateFlow

    private var useCaseMovieDetails: UseCaseMovieDetailsGet? = null

    fun getMovieDetails(movieId: String) {
        moviesMutableStateFlow.value = UIDetailState.LoadingUIState

        viewModelScope.launch {
            useCaseMovieDetails =
                UseCaseMovieDetailsGet(
                    movieId = movieId,
                    repository = repository
                ) { response: MovieDetail ->

                    if (response.responseCode.statusCode == "200"
                        && response.responseCode.httpStatus == "200"
                        && response.errorResponse.message.isBlank()
                    ) {
                        moviesMutableStateFlow.value = UIDetailState.ResultsUIState(response)
                    } else {
                        moviesMutableStateFlow.value = UIDetailState.ErrorUIState
                    }
                }.also { it.execute() }
        }
    }
}