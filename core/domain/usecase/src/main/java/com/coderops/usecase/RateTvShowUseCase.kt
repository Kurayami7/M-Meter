package com.coderops.usecase

import com.coderops.entities.StatusEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class RateTvShowUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(rate: Double, tvShowId: Int): StatusEntity {
        return movieRepository.rateTvShow(rate, tvShowId)
    }
}