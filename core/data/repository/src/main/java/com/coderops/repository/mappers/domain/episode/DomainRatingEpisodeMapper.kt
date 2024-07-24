package com.coderops.repository.mappers.domain.episode

import com.coderops.entities.RatingEpisodeDetailsStatusEntity
import com.coderops.remote.response.dto.episode_details.RatingEpisodeDetailsRemoteDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainRatingEpisodeMapper @Inject constructor() :
    Mapper<RatingEpisodeDetailsRemoteDto, RatingEpisodeDetailsStatusEntity> {

    override fun map(input: RatingEpisodeDetailsRemoteDto): RatingEpisodeDetailsStatusEntity {
        return RatingEpisodeDetailsStatusEntity(
            statusCode = input.statusCode ?: 0,
            statusMessage = input.statusMessage ?: "",
            success = input.success ?: false
        )
    }
}