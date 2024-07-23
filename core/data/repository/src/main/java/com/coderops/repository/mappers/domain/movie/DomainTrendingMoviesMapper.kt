package com.coderops.repository.mappers.domain.movie

import com.coderops.entities.MovieEntity
import com.coderops.local.database.dto.movie.TrendingMoviesLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject


class DomainTrendingMoviesMapper @Inject constructor() :
    Mapper<TrendingMoviesLocalDto, MovieEntity> {

    override fun map(input: TrendingMoviesLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            genreEntities = emptyList(),
            rate = input.rate
        )
    }
}