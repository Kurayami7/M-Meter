package com.coderops.usecase.search

import com.coderops.entities.PeopleEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class SearchPeopleUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(keyword: String): List<PeopleEntity>{
        return movieRepository.searchForPeople(keyword)
    }
}