package com.coderops.usecase.myList

import com.coderops.entities.StatusEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class DeleteListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(listId: Int): StatusEntity {
        return movieRepository.deleteList(listId =listId)
    }
}
