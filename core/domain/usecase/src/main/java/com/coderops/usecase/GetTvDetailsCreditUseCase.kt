package com.coderops.usecase

import com.coderops.entities.PeopleEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetTvDetailsCreditUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(tvShowId:Int): List<PeopleEntity> {
        return movieRepository.getTvDetailsCredit(tvShowId)
    }
}