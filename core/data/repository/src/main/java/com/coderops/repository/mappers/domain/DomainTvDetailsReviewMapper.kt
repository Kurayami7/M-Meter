package com.coderops.repository.mappers.domain

import com.coderops.entities.ReviewEntity
import com.coderops.remote.response.dto.TvReviewRemoteDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainTvDetailsReviewMapper @Inject constructor() : Mapper<TvReviewRemoteDto, ReviewEntity> {
    override fun map(input: List<TvReviewRemoteDto>): List<ReviewEntity> {
        return input.map(::map)
    }

    override fun map(input: TvReviewRemoteDto): ReviewEntity {
        return ReviewEntity(
            name = input.authorDetails?.username ?: "User",
            avatar_path = input.authorDetails?.avatarPath ?: "",
            content = input.content ?: "",
            created_at = input.createdAt ?: ""
        )
    }

}