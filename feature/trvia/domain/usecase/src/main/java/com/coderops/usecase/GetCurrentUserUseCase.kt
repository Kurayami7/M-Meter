package com.coderops.usecase

import com.coderops.entities.UserEntity
import com.coderops.repository.AuthRepository
import com.coderops.repository.TriviaRepository
import com.coderops.repository.UnauthorizedThrowable
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): UserEntity {
        return authRepository.getCurrentUsername()?.let {
            triviaRepository.getUserByUsername(it)
        } ?: throw UnauthorizedThrowable()
    }
}