package com.coderops.usecase.game.questions

import com.coderops.entities.QuestionEntity
import com.coderops.repository.TriviaRepository
import com.coderops.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class GetCurrentPeopleQuestion @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): QuestionEntity {
        val user = getCurrentUserUseCase()
        val level = user.peopleGameLevel
        val questionNumber = user.numPeopleQuestionsPassed
        return triviaRepository.getPeopleQuestion(level, questionNumber)
    }
}