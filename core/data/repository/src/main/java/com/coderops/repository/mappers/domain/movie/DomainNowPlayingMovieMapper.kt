package com.coderops.repository.mappers.domain.movie

import com.coderops.entities.MovieEntity
import com.coderops.local.database.dto.movie.NowPlayingMovieLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainNowPlayingMovieMapper @Inject constructor():
    Mapper<NowPlayingMovieLocalDto, MovieEntity> {

    override fun map(input: NowPlayingMovieLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            genreEntities = emptyList(),
            rate = input.rate
        )
    }
}