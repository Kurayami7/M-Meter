package com.coderops.repository.my_rated

import com.coderops.entities.my_rated.MyRatedTvShowEntity
import com.coderops.local.database.MovieDao
import com.coderops.remote.service.MovieService
import com.coderops.repository.BasePagingSource
import com.coderops.repository.mappers.domain.DomainGenreMapper
import com.coderops.repository.mappers.domain.my_rated.DomainMyRatedTvShowMapper
import javax.inject.Inject

class RatedTvShowPagingSource @Inject constructor(
    service: MovieService,
    private val domainGenreMapper: DomainGenreMapper,
    private val mapper: DomainMyRatedTvShowMapper,
    private val movieDao: MovieDao,
) : BasePagingSource<MyRatedTvShowEntity>(service){
    override suspend fun fetchData(page: Int): List<MyRatedTvShowEntity> {
        val response = service.getRatedTv(page).body()?.results?.filterNotNull()
        val genreMovieMapper = domainGenreMapper.map(movieDao.getGenresMovies())
        return response?.map { mapper.map(it , genreMovieMapper) } ?: emptyList()
    }
}