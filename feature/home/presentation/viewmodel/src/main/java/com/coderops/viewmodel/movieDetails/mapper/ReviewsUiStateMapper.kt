package com.coderops.viewmodel.movieDetails.mapper

import com.coderops.entities.movieDetails.ReviewEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.movieDetails.ReviewUiState
import javax.inject.Inject

class ReviewsUiStateMapper@Inject constructor() :
    Mapper<ReviewEntity, ReviewUiState> {
    override fun map(input: ReviewEntity): ReviewUiState {
        return ReviewUiState(
            name = input.name,
            avatar_path = input.avatar_path,
            content = input.content,
            created_at = input.created_at
        )
    }

}