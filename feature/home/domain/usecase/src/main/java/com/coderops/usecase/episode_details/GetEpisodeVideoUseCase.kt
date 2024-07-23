package com.coderops.usecase.episode_details

import com.coderops.entities.YoutubeVideoDetailsEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetEpisodeVideoUseCase  @Inject constructor(
    private val movieRepository: MovieRepository
)  {
    suspend operator fun invoke(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int,
    ): YoutubeVideoDetailsEntity {
        return movieRepository.getVideoEpisodeDetails(id, seasonNumber, episodeNumber)
    }
}