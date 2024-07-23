package com.coderops.usecase

import com.coderops.entities.TvDetailsInfoEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetTVDetailsInfoUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(tvShowId:Int): TvDetailsInfoEntity{
        return movieRepository.getTvDetailsInfo(tvShowId)
    }
}