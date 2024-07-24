package com.coderops.repository.mappers.domain

import com.coderops.entities.TvShowEntity
import com.coderops.remote.response.dto.TvShowsCastItem
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainTvShowsByPeopleMapper @Inject constructor() : Mapper<TvShowsCastItem?, TvShowEntity> {
    override fun map(input: TvShowsCastItem?): TvShowEntity {

        return TvShowEntity(
            input?.id ?: 0, input?.name ?: "",
            (BuildConfig.IMAGE_BASE_PATH + input?.posterPath),
            rate = (input?.voteAverage
                ?: 0.0) as Double
        )
    }
}
