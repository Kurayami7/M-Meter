package com.coderops.usecase.home

import com.coderops.entities.PeopleEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetPopularPeopleUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val refreshIfNeededUseCase: RefreshIfNeededUseCase
) {
    suspend operator fun invoke(limit: Int = 10): List<PeopleEntity> {
        refreshIfNeededUseCase()
        return movieRepository.getPopularPeopleFromDatabase()
            .also { if (it.isEmpty()) movieRepository.refreshPopularPeople() }
            .take(limit)
    }
}