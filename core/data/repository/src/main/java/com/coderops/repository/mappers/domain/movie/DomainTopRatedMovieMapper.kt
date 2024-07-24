package com.coderops.repository.mappers.domain.movie

import com.coderops.entities.MovieEntity
import com.coderops.local.database.dto.movie.TopRatedMovieLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainTopRatedMovieMapper @Inject constructor() : Mapper<TopRatedMovieLocalDto, MovieEntity> {

    override fun map(input: TopRatedMovieLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            genreEntities = emptyList(),
            rate = input.rate
        )
    }
}