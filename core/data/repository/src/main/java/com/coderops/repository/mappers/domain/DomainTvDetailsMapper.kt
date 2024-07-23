package com.coderops.repository.mappers.domain

import com.coderops.entities.GenreEntity
import com.coderops.entities.TvDetailsInfoEntity
import com.coderops.remote.response.dto.TvDetailsRemoteDto
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainTvDetailsMapper @Inject constructor() :
    Mapper<TvDetailsRemoteDto, TvDetailsInfoEntity> {
    override fun map(input: TvDetailsRemoteDto): TvDetailsInfoEntity {
        return TvDetailsInfoEntity(
            backdropImageUrl = BuildConfig.IMAGE_BASE_PATH + input.backdropPath,
            name = input.name ?: "",
            rating = input.voteAverage?.toFloat()?.times(0.5f) ?: 0.0f,
            description = input.overview ?: "",
            genres = mapGenereToEntity(input.genres)
        )
    }

    private fun mapGenereToEntity(genereRemoteDto: List<TvDetailsRemoteDto.Genre?>?): List<GenreEntity> {
        return genereRemoteDto?.map {
            GenreEntity(
                genreID = it?.id ?: 0,
                genreName = it?.name ?: ""
            )
        } ?: emptyList()
    }
}

