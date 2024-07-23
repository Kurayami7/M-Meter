package com.coderops.usecase

import com.coderops.entities.TvShowEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetTvShowsByPersonUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(person_id:Int): List<TvShowEntity> {
        return movieRepository.getTvShowsByPerson(person_id)
    }
}