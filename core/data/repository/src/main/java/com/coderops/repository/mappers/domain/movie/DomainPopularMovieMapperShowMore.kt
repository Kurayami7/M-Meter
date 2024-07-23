package com.coderops.repository.mappers.domain.movie

import com.coderops.entities.GenreEntity
import com.coderops.entities.MovieEntity
import com.coderops.remote.response.dto.MovieRemoteDto
import com.coderops.repository.BuildConfig
import javax.inject.Inject

class DomainPopularMovieMapperShowMore @Inject constructor() {

    fun map(input: MovieRemoteDto, genreEntities: List<GenreEntity>): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year = input.releaseDate ?: "",
            genreEntities = genreEntities.filter {
                it.genreID in (input.genreIds?.filterNotNull() ?: emptyList())
            },
            rate = input.voteAverage ?: 0.0
        )
    }
}