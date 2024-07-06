package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetMyWatchlistListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<MovieEntity> {
        return  movieRepository.getWatchlistMovies() + movieRepository.getWatchlistTv()
    }
}