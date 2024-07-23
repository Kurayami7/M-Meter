package com.coderops.repository.mappers.domain

import com.coderops.entities.MovieEntity
import com.coderops.remote.response.dto.CastItem
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainMoviesByPeopleMapper @Inject constructor() : Mapper<CastItem?, MovieEntity> {
    override fun map(input: CastItem?): MovieEntity {

        return MovieEntity(
            id = input?.id ?: 0,
            title = input?.title ?: "",
            imageUrl = (BuildConfig.IMAGE_BASE_PATH + input?.posterPath),
            genreEntities = emptyList(),
            rate = input?.voteAverage
                ?: 0.0
        )

    }
}