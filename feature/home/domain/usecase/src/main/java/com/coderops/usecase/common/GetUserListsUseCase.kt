package com.coderops.usecase.common

import com.coderops.entities.UserListEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetUserListsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<UserListEntity> {
        return movieRepository.getUserLists()
    }
}