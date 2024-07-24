package com.coderops.usecase.myList

import com.coderops.entities.StatusEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class DeleteMovieFromDetailsListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(listId: Int,mediaId: Int ): StatusEntity {
        return movieRepository.deleteMovieDetailsList(listId =listId , mediaId = mediaId )
    }
}