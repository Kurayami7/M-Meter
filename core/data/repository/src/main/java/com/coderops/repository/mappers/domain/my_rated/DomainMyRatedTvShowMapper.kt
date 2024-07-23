package com.coderops.repository.mappers.domain.my_rated

import com.coderops.entities.GenreEntity
import com.coderops.entities.my_rated.MyRatedTvShowEntity
import com.coderops.remote.response.dto.my_rated.MyRatedTvShowDto
import com.coderops.repository.BuildConfig
import javax.inject.Inject

class DomainMyRatedTvShowMapper @Inject constructor() {

    fun map(input: MyRatedTvShowDto, genreEntities: List<GenreEntity>): MyRatedTvShowEntity {
        return MyRatedTvShowEntity(
            id = input.id ?: 0,
            title = input.name ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            genreEntities = genreEntities.filter {
                it.genreID in (input.genreIds?.filterNotNull() ?: emptyList())
            },
            rate = input.rating ?: 0.0,
            year = input.firstAirDate ?: ""
        )
    }
}