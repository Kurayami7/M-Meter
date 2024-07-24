package com.coderops.repository.mappers.domain.movie

import com.coderops.entities.GenreEntity
import com.coderops.entities.MovieEntity
import com.coderops.remote.response.dto.TvRemoteDto
import com.coderops.repository.BuildConfig
import javax.inject.Inject

class DomainTvMapper @Inject constructor() {
    fun map(input: TvRemoteDto, genres: List<GenreEntity> , mediaType:String="tv"): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            title = input.name ?: "",
            rate = input.voteAverage ?: 0.0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year = input.firstAirDate ?: "",
            genreEntities = genres,
            mediaType = mediaType,
        )
    }
}