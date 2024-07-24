package com.coderops.usecase

import com.coderops.entities.MovieEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetMoviesByPersonUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(person_id:Int): List<MovieEntity>{
        return movieRepository.getMoviesByPerson(person_id)
    }
}