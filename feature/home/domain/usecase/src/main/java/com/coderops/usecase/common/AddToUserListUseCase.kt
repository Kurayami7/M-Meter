package com.coderops.usecase.common

import com.coderops.entities.StatusEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class AddToUserListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(listId: Int, mediaId: Int): StatusEntity {
        return movieRepository.postUserLists(listId, mediaId)
    }
}