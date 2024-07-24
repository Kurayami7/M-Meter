package com.coderops.repository.mappers.domain

import com.coderops.entities.GenreEntity
import com.coderops.local.database.dto.GenresTvsLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainGenreTvMapper @Inject constructor() : Mapper<GenresTvsLocalDto, GenreEntity> {
    override fun map(input: GenresTvsLocalDto): GenreEntity {
        return GenreEntity(
            genreID =  input.id,
            genreName = input.name
        )
    }
}