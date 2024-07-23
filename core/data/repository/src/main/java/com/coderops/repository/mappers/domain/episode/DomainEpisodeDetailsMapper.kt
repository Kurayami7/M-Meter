package com.coderops.repository.mappers.domain.episode

import com.coderops.entities.EpisodeDetailsEntity
import com.coderops.remote.response.dto.episode_details.EpisodeDetailsRemoteDto
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainEpisodeDetailsMapper @Inject constructor() :
    Mapper<EpisodeDetailsRemoteDto, EpisodeDetailsEntity> {
    override fun map(input: EpisodeDetailsRemoteDto): EpisodeDetailsEntity {
        return EpisodeDetailsEntity(
            id = input.id ?: 0,
            overview = input.overview ?: "",
            productionCode = input.productionCode ?: "",
            seasonNumber = input.seasonNumber ?: 0,
            episodeNumber = input.episodeNumber ?: 0,
            episodeName = input.name ?: "",
            voteAverage = input.voteAverage ?: 0f,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.stillPath,
            episodeRate = input.voteAverage ?: 0.0F
        )
    }

}