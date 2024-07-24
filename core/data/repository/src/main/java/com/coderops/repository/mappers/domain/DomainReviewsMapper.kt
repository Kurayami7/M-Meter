package com.coderops.repository.mappers.domain

import com.coderops.entities.movieDetails.ReviewEntity
import com.coderops.entities.movieDetails.ReviewResponseEntity
import com.coderops.remote.response.movieDetails.ReviewsDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainReviewsMapper @Inject constructor() : Mapper<ReviewsDto, ReviewResponseEntity> {
    override fun map(input: ReviewsDto): ReviewResponseEntity {
        return ReviewResponseEntity(
            reviews = input.results?.map {
                ReviewEntity(
                    name = it.author ?: "",
                    avatar_path = it.authorDetails?.avatarPath ?: "",
                    content = it.content ?: "",
                    created_at = it.createdAt ?: ""
                )
            } ?: emptyList(),
            totalPages = input.totalPages ?: 0,
            page = input.page ?: 0,
            totalResults = input.totalResults ?: 0
        )
    }
}