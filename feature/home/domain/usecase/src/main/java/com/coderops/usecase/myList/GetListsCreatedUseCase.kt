package com.coderops.usecase.myList

import com.coderops.entities.myList.ListCreatedEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetListsCreatedUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<ListCreatedEntity> {
        return movieRepository.getListCreated()
    }
}