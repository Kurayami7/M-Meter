package com.coderops.repository

import com.coderops.entities.MovieInWatchHistoryEntity
import com.coderops.local.database.MovieDao
import com.coderops.repository.mappers.cash.movie.LocalInWatchHistoryMoviesMapper
import com.coderops.repository.mappers.domain.movie.DomainInWatchHistoryMoviesMapper
import com.coderops.usecase.watch_history.WatchHistoryRepository
import javax.inject.Inject

class WatchHistoryRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val domainInWatchHistoryMoviesMapper: DomainInWatchHistoryMoviesMapper,
    private val localInWatchHistoryMoviesMapper: LocalInWatchHistoryMoviesMapper
) : BaseRepository(), WatchHistoryRepository {

    override suspend fun insertMovieToWatchHistory(movieInWatchHistoryEntity: MovieInWatchHistoryEntity) {
        movieDao.insertMovieToWatchHistory(
            localInWatchHistoryMoviesMapper.map(
                movieInWatchHistoryEntity
            )
        )
    }

    override suspend fun deleteMovieFromWatchHistory(movieInWatchHistoryEntity: MovieInWatchHistoryEntity) {
        movieDao.deleteMovieFromWatchHistory(
            localInWatchHistoryMoviesMapper.map(
                movieInWatchHistoryEntity
            )
        )
    }

    override suspend fun getAllMoviesInWatchHistory(): List<MovieInWatchHistoryEntity> {
        return movieDao.getAllWatchHistoryVideos().map {
            domainInWatchHistoryMoviesMapper.map(it)
        }
    }

    override suspend fun searchWatchHistoryWithKeyWord(keyword: String): List<MovieInWatchHistoryEntity> {
        return movieDao.searchWatchHistory("%${keyword}%").map {
            domainInWatchHistoryMoviesMapper.map(it)
        }
    }


}