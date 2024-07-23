package com.coderops.repository.mappers.cash.movie

import com.coderops.local.database.dto.movie.TrendingMoviesLocalDto
import com.coderops.remote.response.dto.MovieRemoteDto
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class LocalTrendingMoviesMapper @Inject constructor() :
    Mapper<MovieRemoteDto, TrendingMoviesLocalDto> {
    override fun map(input: MovieRemoteDto): TrendingMoviesLocalDto {
        return TrendingMoviesLocalDto(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rate = input.voteAverage ?: 0.0
        )
    }
}