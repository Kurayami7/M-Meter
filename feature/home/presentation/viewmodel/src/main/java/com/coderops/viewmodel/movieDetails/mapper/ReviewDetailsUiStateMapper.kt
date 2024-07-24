package com.coderops.viewmodel.movieDetails.mapper

import com.coderops.entities.movieDetails.MovieDetailsEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.movieDetails.ReviewDetailsUiState
import javax.inject.Inject

class ReviewDetailsUiStateMapper@Inject constructor() :
    Mapper<MovieDetailsEntity, ReviewDetailsUiState> {
    override fun map(input: MovieDetailsEntity): ReviewDetailsUiState {
        return ReviewDetailsUiState(
            page = input.reviewEntity.page,
            totalPages = input.reviewEntity.totalPages,
            totalReviews = input.reviewEntity.totalResults
        )
    }

}