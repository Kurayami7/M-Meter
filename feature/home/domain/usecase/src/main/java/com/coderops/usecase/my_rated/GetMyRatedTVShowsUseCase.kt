package com.coderops.usecase.my_rated

import androidx.paging.PagingData
import com.coderops.entities.my_rated.MyRatedTvShowEntity
import com.coderops.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMyRatedTVShowsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<PagingData<MyRatedTvShowEntity>> {
        return movieRepository.getRatedTvShows().flow
    }
}