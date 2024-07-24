package com.coderops.repository.mappers.cash

import com.coderops.local.database.dto.GenresMoviesLocalDto
import com.coderops.remote.response.dto.GenreMovieRemoteDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class LocalGenresMovieMapper @Inject constructor() :
    Mapper<GenreMovieRemoteDto, GenresMoviesLocalDto> {

    override fun map(input: GenreMovieRemoteDto): GenresMoviesLocalDto {
        return GenresMoviesLocalDto(
            id = input.id ?: 0,
            name = input.name ?: ""
        )
    }
}