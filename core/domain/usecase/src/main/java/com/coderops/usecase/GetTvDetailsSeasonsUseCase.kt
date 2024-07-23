package com.coderops.usecase

import com.coderops.entities.SeasonEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetTvDetailsSeasonsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(tvShowId:Int): List<SeasonEntity> {
        return movieRepository.getTvDetailsSeasons(tvShowId)
    }
}