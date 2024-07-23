package com.coderops.viewmodel.watch_history.state_managment

sealed interface WatchHistoryUiEvent {
    data class NavigateToMovieDetails(val movieId: Int) : WatchHistoryUiEvent
    object ShowDeleteSnackBar : WatchHistoryUiEvent
    object OnClickBack:WatchHistoryUiEvent
    data class Error(val message: String) : WatchHistoryUiEvent
}