package com.coderops.usecase.game.levelup

import com.coderops.repository.TriviaRepository
import com.coderops.usecase.GetCurrentUserUseCase
import javax.inject.Inject


class ResetLevelUpMemorizeUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): Boolean {
        val oldUser = getCurrentUserUseCase()
        if (oldUser.memorizeGameLevel == 1 ) return false
        val updatedUser = oldUser.copy(
            memorizeGameLevel = 1,
        )
        triviaRepository.updateUser(updatedUser)
        return true
    }
}