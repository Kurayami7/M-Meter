package com.coderops.viewmodel.people_guessing

import com.coderops.entities.QuestionEntity
import com.coderops.entities.UserEntity
import com.coderops.usecase.GetCurrentUserUseCase
import com.coderops.usecase.game.UpdateUserPointsUseCase
import com.coderops.usecase.game.levelup.LevelUpPeopleUseCase
import com.coderops.usecase.game.questions.GetCurrentPeopleQuestion
import com.coderops.usecase.game.questions.UpdatePeopleQuestionCountUseCase
import com.coderops.viewmodel.common.BaseTriviaAnswerGameViewModel
import com.coderops.viewmodel.common.model.GameType
import com.coderops.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PeopleGuessingViewModel @Inject constructor(
    override val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentPeopleQuestion: GetCurrentPeopleQuestion,
    private val updatePeopleQuestionCountUseCase: UpdatePeopleQuestionCountUseCase,
    override val updateUserPointsUseCase: UpdateUserPointsUseCase,
    private val levelUpPeopleUseCase: LevelUpPeopleUseCase,
) : BaseTriviaAnswerGameViewModel(state = GameUiState()) {
    override val gameType = GameType.PEOPLE

    init {
        getData()
    }

    override val getQuestion: suspend () -> QuestionEntity
        get() = getCurrentPeopleQuestion::invoke

    override fun onSuccessUser(user: UserEntity) {
        _state.update {
            it.copy(
                level = user.peopleGameLevel,
                points = user.totalPoints,
                questionCount = user.numPeopleQuestionsPassed + 1,
                isError = false,
                isLoading = false
            )
        }
    }

    override suspend fun levelUpUseCase() {
        levelUpPeopleUseCase()
    }

    override suspend fun updateQuestionCountUseCase(questionCount: Int) {
        updatePeopleQuestionCountUseCase(questionCount)
    }
}