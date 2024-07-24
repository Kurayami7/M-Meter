package com.coderops.usecase.season_details

import com.coderops.entities.season_details.SeasonDetailsEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetSeasonDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(seriesId : Int, seasonNumber : Int): SeasonDetailsEntity {
        return movieRepository.getSeasonDetails(seriesId,seasonNumber)
    }
}