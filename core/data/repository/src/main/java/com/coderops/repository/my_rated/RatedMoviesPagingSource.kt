package com.coderops.repository.my_rated


import com.coderops.entities.my_rated.MyRatedMovieEntity
import com.coderops.local.database.MovieDao
import com.coderops.remote.service.MovieService
import com.coderops.repository.BasePagingSource
import com.coderops.repository.mappers.domain.DomainGenreMapper
import com.coderops.repository.mappers.domain.my_rated.DomainMyRatedMoviesMapper
import javax.inject.Inject

class RatedMoviesPagingSource @Inject constructor(
    service: MovieService,
    private val domainGenreMapper: DomainGenreMapper,
    private val mapper: DomainMyRatedMoviesMapper,
    private val movieDao: MovieDao,
) : BasePagingSource<MyRatedMovieEntity>(service) {
    override suspend fun fetchData(page: Int): List<MyRatedMovieEntity> {
        val response = service.getRatedMovies(page).body()?.results?.filterNotNull()
        val genreMovieMapper = domainGenreMapper.map(movieDao.getGenresMovies())
        return response?.map { mapper.map(it , genreMovieMapper) } ?: emptyList()
    }

}