package com.coderops.usecase.myList

import com.coderops.entities.MovieEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetMyWatchlistListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<MovieEntity> {
        return  movieRepository.getWatchlistMovies() + movieRepository.getWatchlistTv()
    }
}