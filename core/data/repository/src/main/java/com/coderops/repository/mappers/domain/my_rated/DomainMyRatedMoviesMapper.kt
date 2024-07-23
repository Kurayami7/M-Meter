package com.coderops.repository.mappers.domain.my_rated

import com.coderops.entities.GenreEntity
import com.coderops.entities.my_rated.MyRatedMovieEntity
import com.coderops.remote.response.dto.my_rated.MyRatedMovieDto
import com.coderops.repository.BuildConfig
import javax.inject.Inject

class DomainMyRatedMoviesMapper @Inject constructor() {
    fun map(input: MyRatedMovieDto ,genreEntities: List<GenreEntity>): MyRatedMovieEntity {
        return MyRatedMovieEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            genreEntities = genreEntities.filter {
                it.genreID in (input.genreIds?.filterNotNull() ?: emptyList())
            },
            myRate = input.rating ?: 0.0,
            year = input.releaseDate ?: ""
        )
    }
}