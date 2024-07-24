package com.coderops.usecase.showmore

import androidx.paging.PagingData
import com.coderops.entities.MovieEntity
import com.coderops.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoreTrendingByTypeUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<PagingData<MovieEntity>> {
        return movieRepository.getTrendingMoviesPaging().flow
    }
}