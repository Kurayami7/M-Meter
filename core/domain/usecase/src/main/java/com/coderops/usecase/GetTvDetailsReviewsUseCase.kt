package com.coderops.usecase

import com.coderops.entities.ReviewEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetTvDetailsReviewsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(tvShowId:Int): List<ReviewEntity> {
        val items = movieRepository.getTvShowReviews(tvShowId)
        return items
    }
}