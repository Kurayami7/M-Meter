package com.chocolatecake.repository.mappers.cash.movie

import com.chocolatecake.local.database.dto.movie.TopRatedMovieLocalDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalTopRatedMovieMapper @Inject constructor():
    Mapper<MovieRemoteDto, TopRatedMovieLocalDto> {
    override fun map(input: MovieRemoteDto): TopRatedMovieLocalDto {
        return TopRatedMovieLocalDto(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rate = input.voteAverage ?: 0.0
        )
    }
}