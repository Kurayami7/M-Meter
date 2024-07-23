package com.coderops.usecase.game

import com.coderops.entities.BoardEntity
import com.coderops.repository.TriviaRepository
import com.coderops.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class GetCurrentBoardUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) {
    suspend operator fun invoke(): BoardEntity {
        val user = getCurrentUserUseCase()
        val level = user.memorizeGameLevel

        return triviaRepository.getBoard(level)
    }
}