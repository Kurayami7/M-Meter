package com.coderops.repository.mappers.cash

import com.coderops.local.database.dto.GenresTvsLocalDto
import com.coderops.remote.response.dto.GenreTVRemoteDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class LocalGenresTvMapper @Inject constructor() :
    Mapper<GenreTVRemoteDto, GenresTvsLocalDto> {

    override fun map(input: GenreTVRemoteDto): GenresTvsLocalDto {
        return GenresTvsLocalDto(
            id = input.id ?: 0,
            name = input.name ?: ""
        )
    }
}