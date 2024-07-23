package com.coderops.usecase.game.questions

import com.coderops.entities.QuestionEntity
import com.coderops.repository.TriviaRepository
import com.coderops.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class GetCurrentTvShowQuestion @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): QuestionEntity {
        val user = getCurrentUserUseCase()
        val level = user.tvShowGameLevel
        val questionNumber = user.numTvShowQuestionsPassed
        return triviaRepository.getTvShowQuestion(level, questionNumber)
    }
}