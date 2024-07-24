package com.coderops.repository.mappers.domain

import com.coderops.entities.SeasonEntity
import com.coderops.remote.response.dto.TvDetailsRemoteDto
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainTvDetailsSeasonMapper @Inject constructor() :Mapper<TvDetailsRemoteDto,List<SeasonEntity>>{
    override fun map(input: TvDetailsRemoteDto): List<SeasonEntity> {
        return input.seasons?.map { season ->
             SeasonEntity(
                 id = season?.id ?: 0,
                 imageUrl = BuildConfig.IMAGE_BASE_PATH + season?.posterPath,
                 title = season?.name ?: "",
                 description = season?.overview ?: "",
                 year = season?.airDate ?: "",
                 countEpisode = season?.episodeCount ?: 0,
                 seasonNumber = season?.seasonNumber ?: 0
            )
        }?: emptyList()
    }
}