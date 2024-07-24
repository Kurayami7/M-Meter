package com.coderops.viewmodel.tv_details.mappers

import com.coderops.entities.ReviewEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.CommentUIState
import javax.inject.Inject

class TvDetailsReviewUiMapper @Inject constructor() :
    Mapper<ReviewEntity, CommentUIState> {
    override fun map(input: List<ReviewEntity>): List<CommentUIState> {
        return input.map(::map)
    }

    override fun map(input: ReviewEntity): CommentUIState {
        return CommentUIState(
            name = input.name,
            content = input.content
        )
    }
}