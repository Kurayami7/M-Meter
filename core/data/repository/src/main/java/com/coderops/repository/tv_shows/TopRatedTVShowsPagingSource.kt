package com.coderops.repository.tv_shows

import com.coderops.entities.TVShowsEntity
import com.coderops.remote.service.MovieService
import com.coderops.repository.BasePagingSource
import com.coderops.repository.mappers.domain.tv.TVShowsDomainMapper
import javax.inject.Inject

class TopRatedTVShowsPagingSource @Inject constructor(
    service: MovieService,
    private val mapper: TVShowsDomainMapper
) : BasePagingSource<TVShowsEntity>(service) {

    override suspend fun fetchData(page: Int): List<TVShowsEntity> {

        val response = service.getTopRatedTVShows(page).body()?.results?.filterNotNull()
        return response?.map { mapper.map(it) } ?: emptyList()
    }
}