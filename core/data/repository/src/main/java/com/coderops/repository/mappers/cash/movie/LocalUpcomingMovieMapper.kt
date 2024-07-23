package com.coderops.repository.mappers.cash.movie

import com.coderops.local.database.dto.movie.UpcomingMovieLocalDto
import com.coderops.remote.response.dto.MovieRemoteDto
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class LocalUpcomingMovieMapper @Inject constructor():
    Mapper<MovieRemoteDto, UpcomingMovieLocalDto> {
    override fun map(input: MovieRemoteDto): UpcomingMovieLocalDto {
        return UpcomingMovieLocalDto(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rate = input.voteAverage ?: 0.0
        )
    }
}