package com.coderops.repository.mappers.domain.movie

import com.coderops.entities.MovieEntity
import com.coderops.local.database.dto.movie.UpcomingMovieLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainUpcomingMovieMapper @Inject constructor(): Mapper<UpcomingMovieLocalDto, MovieEntity> {

    override fun map(input: UpcomingMovieLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            genreEntities = emptyList(),
            rate = input.rate
        )
    }
}