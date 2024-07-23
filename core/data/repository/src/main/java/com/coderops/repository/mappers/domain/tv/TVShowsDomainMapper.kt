package com.coderops.repository.mappers.domain.tv

import com.coderops.entities.TVShowsEntity
import com.coderops.remote.response.dto.TVShowsRemoteDto
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class TVShowsDomainMapper @Inject constructor() :
    Mapper<TVShowsRemoteDto, TVShowsEntity> {

    override fun map(input: TVShowsRemoteDto): TVShowsEntity {
        return TVShowsEntity(
            id = input.id ?: 0,
            title = input.name ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath ,
            genreEntities = emptyList(),
            rate = input.voteAverage ?: 0.0
        )
    }
}