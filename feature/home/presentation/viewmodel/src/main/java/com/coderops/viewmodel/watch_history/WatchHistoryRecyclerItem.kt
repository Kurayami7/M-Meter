package com.coderops.viewmodel.watch_history

import com.coderops.viewmodel.watch_history.state_managment.MovieUiState


sealed class WatchHistoryRecyclerItem {
    data class MovieCard(val movie: MovieUiState) : WatchHistoryRecyclerItem()
    data class Title(val title: String) : WatchHistoryRecyclerItem()
}
