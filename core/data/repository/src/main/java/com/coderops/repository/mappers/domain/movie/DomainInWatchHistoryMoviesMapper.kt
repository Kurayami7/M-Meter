package com.coderops.repository.mappers.domain.movie

import com.coderops.entities.MovieInWatchHistoryEntity
import com.coderops.local.database.dto.movie.MovieInWatchHistoryLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainInWatchHistoryMoviesMapper @Inject constructor()
    :Mapper<MovieInWatchHistoryLocalDto,MovieInWatchHistoryEntity>{
    override fun map(input: MovieInWatchHistoryLocalDto): MovieInWatchHistoryEntity {
        return MovieInWatchHistoryEntity(
            id = input.id,
            title = input.title,
            description = input.title,
            voteAverage = input.voteAverage,
            dateWatched = input.dateWatched,
            posterPath = input.posterPath,
            year = input.year
        )
    }
}