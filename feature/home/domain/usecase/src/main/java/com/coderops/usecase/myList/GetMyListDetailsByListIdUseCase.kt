package com.coderops.usecase.myList

import com.coderops.entities.MovieEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetMyListDetailsByListIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(listId: Int = 0): List<MovieEntity> {
        return  movieRepository.getDetailsList(listId)
    }
}