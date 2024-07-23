package com.coderops.usecase.game.questions

import com.coderops.entities.QuestionEntity
import com.coderops.repository.TriviaRepository
import com.coderops.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class GetCurrentMovieQuestion @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): QuestionEntity {
        val user = getCurrentUserUseCase()
        val level = user.moviesGameLevel
        val questionNumber = user.numMoviesQuestionsPassed
        return triviaRepository.getMovieQuestion(level, questionNumber)
    }
}