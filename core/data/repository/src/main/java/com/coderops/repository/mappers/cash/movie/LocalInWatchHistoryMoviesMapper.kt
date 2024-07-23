package com.coderops.repository.mappers.cash.movie

import com.coderops.entities.MovieInWatchHistoryEntity
import com.coderops.local.database.dto.movie.MovieInWatchHistoryLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class LocalInWatchHistoryMoviesMapper @Inject constructor()
    :Mapper<MovieInWatchHistoryEntity,MovieInWatchHistoryLocalDto> {
    override fun map(input: MovieInWatchHistoryEntity): MovieInWatchHistoryLocalDto {
        return MovieInWatchHistoryLocalDto(
            id = input.id,
            posterPath = input.posterPath,
            title = input.title,
            voteAverage = input.voteAverage,
            description = input.description,
            dateWatched = input.dateWatched,
            year = input.year
        )
    }
}