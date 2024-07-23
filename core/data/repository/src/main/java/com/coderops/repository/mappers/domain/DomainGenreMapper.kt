package com.coderops.repository.mappers.domain

import com.coderops.entities.GenreEntity
import com.coderops.local.database.dto.GenresMoviesLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainGenreMapper @Inject constructor() : Mapper<GenresMoviesLocalDto, GenreEntity> {
    override fun map(input: GenresMoviesLocalDto): GenreEntity {
        return GenreEntity(
            genreID =  input.id,
            genreName = input.name
        )
    }
}