package com.coderops.usecase.watch_history


import com.coderops.entities.MovieInWatchHistoryEntity
import javax.inject.Inject

class GetAllWatchHistoryMoviesUseCase @Inject constructor(
    private val repository: WatchHistoryRepository
) {
    suspend operator fun invoke(): List<MovieInWatchHistoryEntity> {
        return repository.getAllMoviesInWatchHistory()
    }

}