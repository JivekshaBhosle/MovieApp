package com.movieapp.presentation.viewmodel.state

import com.movieapp.domain.model.MovieDetail

sealed class UIDetailState {
    data class ResultsUIState(val movieDetails: MovieDetail) : UIDetailState()
    object ErrorUIState : UIDetailState()
    object EmptyUIState : UIDetailState()
    object LoadingUIState : UIDetailState()
}