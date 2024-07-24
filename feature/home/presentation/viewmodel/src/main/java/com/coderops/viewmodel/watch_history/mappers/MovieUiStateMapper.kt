package com.coderops.viewmodel.watch_history.mappers

import com.coderops.entities.MovieInWatchHistoryEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.watch_history.state_managment.MovieUiState
import javax.inject.Inject

class MovieUiStateMapper @Inject constructor() : Mapper<MovieInWatchHistoryEntity, MovieUiState> {
    override fun map(input: MovieInWatchHistoryEntity): MovieUiState {
        return MovieUiState(
            id = input.id,
            title = input.title,
            description = input.description,
            rating = input.voteAverage,
            imageUrl = input.posterPath,
            dateWatched = input.dateWatched,
            year = input.year
        )
    }
}