package com.coderops.usecase.episode_details

import com.coderops.entities.RatingEpisodeDetailsStatusEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class SetEpisodeRatingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        value: Float
    ): RatingEpisodeDetailsStatusEntity {
        return movieRepository.setRatingForEpisode(seriesId, seasonNumber, episodeNumber, value)
    }
}