package com.coderops.usecase.episode_details

import android.util.Log
import com.coderops.entities.EpisodeDetailsEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetEpisodeDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): EpisodeDetailsEntity {
        Log.d(
            "banan-data-usecase",
            movieRepository.getEpisodeDetails(seriesId, seasonNumber, episodeNumber).episodeName
        )
        return movieRepository.getEpisodeDetails(seriesId, seasonNumber, episodeNumber)
    }
}