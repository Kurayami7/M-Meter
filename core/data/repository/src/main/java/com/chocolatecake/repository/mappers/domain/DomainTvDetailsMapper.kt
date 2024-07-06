package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.remote.response.dto.TvDetailsRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
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

