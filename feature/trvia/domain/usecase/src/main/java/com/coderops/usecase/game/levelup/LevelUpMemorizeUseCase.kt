package com.coderops.usecase.game.levelup

import com.coderops.repository.TriviaRepository
import com.coderops.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class LevelUpMemorizeUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): Boolean {
        val oldUser = getCurrentUserUseCase()
        if (oldUser.memorizeGameLevel == MAX_LEVEL) return false
        val updatedUser = oldUser.copy(
            memorizeGameLevel = oldUser.memorizeGameLevel + 1
        )
        triviaRepository.updateUser(updatedUser)
        return true
    }

    private companion object {
        const val MAX_LEVEL = 3
    }
}