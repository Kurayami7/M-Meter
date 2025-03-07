package com.coderops.usecase.home

import com.coderops.entities.MovieEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val refreshIfNeededUseCase: RefreshIfNeededUseCase
) {
    suspend operator fun invoke(limit: Int = 10): List<MovieEntity> {
        refreshIfNeededUseCase()
        return movieRepository.getPopularMoviesFromDatabase()
            .also { if (it.isEmpty()) movieRepository.refreshPopularMovies() }
            .take(limit)
    }
}