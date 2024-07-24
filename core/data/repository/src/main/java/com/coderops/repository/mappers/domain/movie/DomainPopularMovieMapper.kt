package com.coderops.repository.mappers.domain.movie

import com.coderops.entities.MovieEntity
import com.coderops.local.database.dto.movie.PopularMovieLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainPopularMovieMapper @Inject constructor():
    Mapper<PopularMovieLocalDto, MovieEntity> {

    override fun map(input: PopularMovieLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            genreEntities = emptyList(),
            rate = input.rate
        )
    }
}