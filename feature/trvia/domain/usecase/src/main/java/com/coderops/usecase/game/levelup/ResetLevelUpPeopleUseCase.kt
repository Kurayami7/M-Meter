package com.coderops.usecase.game.levelup

import com.coderops.repository.TriviaRepository
import com.coderops.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class ResetLevelUpPeopleUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): Boolean {
        val oldUser = getCurrentUserUseCase()
        if (oldUser.peopleGameLevel == 1 && oldUser.numPeopleQuestionsPassed == 0) return false
        val updatedUser = oldUser.copy(
            peopleGameLevel = 1,
            numPeopleQuestionsPassed = 0
        )
        triviaRepository.updateUser(updatedUser)
        return true
    }
}