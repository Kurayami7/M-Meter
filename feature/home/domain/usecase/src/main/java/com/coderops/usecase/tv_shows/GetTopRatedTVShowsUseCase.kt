package com.coderops.usecase.tv_shows

import androidx.paging.PagingData
import com.coderops.entities.TVShowsEntity
import com.coderops.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedTVShowsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<PagingData<TVShowsEntity>> {
        return movieRepository.getTopRatedTVShows().flow
    }
}