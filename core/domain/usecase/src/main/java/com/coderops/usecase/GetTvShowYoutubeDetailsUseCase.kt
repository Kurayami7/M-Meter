package com.coderops.usecase

import com.coderops.entities.YoutubeVideoDetailsEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetTvShowYoutubeDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(tvShowId: Int): YoutubeVideoDetailsEntity {
        return movieRepository.getTrailerVideoForTvShow(tvShowId)
    }
}