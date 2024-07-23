package com.coderops.repository.mappers.domain.movie

import com.coderops.entities.GenreEntity
import com.coderops.remote.response.movieDetails.Genre
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainMainGenreMapper @Inject constructor() : Mapper<Genre, GenreEntity> {
    override fun map(input: Genre): GenreEntity {
        return GenreEntity(
            genreID =  input.id?:0,
            genreName = input.name?:"",
        )
    }
}