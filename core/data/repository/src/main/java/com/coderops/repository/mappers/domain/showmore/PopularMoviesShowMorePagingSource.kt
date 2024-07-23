package com.coderops.repository.mappers.domain.showmore

import com.coderops.entities.MovieEntity
import com.coderops.local.database.MovieDao
import com.coderops.remote.service.MovieService
import com.coderops.repository.BasePagingSource
import com.coderops.repository.mappers.domain.DomainGenreMapper
import com.coderops.repository.mappers.domain.movie.DomainPopularMovieMapperShowMore
import javax.inject.Inject

class PopularMoviesShowMorePagingSource @Inject constructor(
    service: MovieService,
    private val mapper: DomainPopularMovieMapperShowMore,
    private val domainGenreMapper: DomainGenreMapper,
    private val movieDao: MovieDao,

    ) : BasePagingSource<MovieEntity>(service) {

    override suspend fun fetchData(page: Int): List<MovieEntity> {
        val response = service.getPopularMovies(page).body()?.results?.filterNotNull()
        val genreMovieMapper = domainGenreMapper.map(movieDao.getGenresMovies())
        return response?.map { mapper.map(it, genreMovieMapper) } ?: emptyList()
    }
}