package com.chocolatecake.usecase.my_rated

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.entities.my_rated.MyRatedTvShowEntity
import com.chocolatecake.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetMyRatedTVShowsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<PagingData<MyRatedTvShowEntity>> {
        return movieRepository.getRatedTvShows().flow
    }
}