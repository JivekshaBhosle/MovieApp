package com.movieapp.presentation.viewmodel.state

import com.movieapp.domain.model.MovieSection

sealed class UIState {
    data class ListResultsUIState(val movieSections: List<MovieSection>) : UIState()
    object ErrorUIState : UIState()
    object EmptyUIState : UIState()
    object LoadingUIState : UIState()
}