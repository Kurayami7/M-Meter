package com.coderops.usecase

import com.coderops.entities.TvShowEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetTvShowRecommendationsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(tvShowId:Int):List<TvShowEntity>{
        return movieRepository.getTvShowRecommendations(tvShowId)
    }
}