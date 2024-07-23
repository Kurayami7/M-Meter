package com.coderops.usecase.game.questions

import com.coderops.entities.UserEntity
import com.coderops.repository.TriviaRepository
import com.coderops.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class UpdatePeopleQuestionCountUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(numPeopleQuestionsPassed: Int): Boolean {
        val oldUser = getCurrentUserUseCase()
        val maxQuestionCount = getMaxQuestionCountForUser(oldUser)
        if (numPeopleQuestionsPassed > maxQuestionCount) return false

        val updatedUser = oldUser.copy(numPeopleQuestionsPassed = numPeopleQuestionsPassed)
        triviaRepository.updateUser(updatedUser)
        return true
    }

    private fun getMaxQuestionCountForUser(oldUser: UserEntity): Int {
        return when (oldUser.peopleGameLevel) {
            1 -> MAX_QUESTIONS_COUNT_IN_LEVEL1
            2 -> MAX_QUESTIONS_COUNT_IN_LEVEL2
            else -> MAX_QUESTIONS_COUNT_IN_LEVEL3
        }
    }

    private companion object {
        const val MAX_QUESTIONS_COUNT_IN_LEVEL1 = 5
        const val MAX_QUESTIONS_COUNT_IN_LEVEL2 = 10
        const val MAX_QUESTIONS_COUNT_IN_LEVEL3 = 15

    }
}