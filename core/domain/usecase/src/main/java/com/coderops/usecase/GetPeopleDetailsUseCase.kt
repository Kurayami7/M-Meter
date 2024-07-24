package com.coderops.usecase

import com.coderops.entities.PeopleDetailsEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetPeopleDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(person_id:Int): PeopleDetailsEntity{
        return movieRepository.getPersonDetails(person_id)
    }
}