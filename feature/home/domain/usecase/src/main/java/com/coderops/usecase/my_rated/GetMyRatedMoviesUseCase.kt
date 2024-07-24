package com.coderops.usecase.my_rated

import androidx.paging.PagingData
import com.coderops.entities.my_rated.MyRatedMovieEntity
import com.coderops.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMyRatedMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<PagingData<MyRatedMovieEntity>> {
        return movieRepository.getRatedMovies().flow
    }
}