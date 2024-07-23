package com.coderops.usecase.game

import com.coderops.repository.TriviaRepository
import com.coderops.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class UpdateUserPointsUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) {
    suspend operator fun invoke(points: Int) {
        val oldUser = getCurrentUserUseCase()
        val updatedUser = oldUser.copy(
            totalPoints =  points
        )
        triviaRepository.updateUser(updatedUser)
    }
}
