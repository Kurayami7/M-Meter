package com.coderops.viewmodel.tv_show_guessing

import com.coderops.entities.QuestionEntity
import com.coderops.entities.UserEntity
import com.coderops.usecase.GetCurrentUserUseCase
import com.coderops.usecase.game.UpdateUserPointsUseCase
import com.coderops.usecase.game.levelup.LevelUpTvShowUseCase
import com.coderops.usecase.game.questions.GetCurrentTvShowQuestion
import com.coderops.usecase.game.questions.UpdateTvShowsQuestionCountUseCase
import com.coderops.viewmodel.common.BaseTriviaAnswerGameViewModel
import com.coderops.viewmodel.common.model.GameType
import com.coderops.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TvShowGuessingViewModel @Inject constructor(
    override val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentTvShowQuestion: GetCurrentTvShowQuestion,
    private val updateTvShowsQuestionCountUseCase: UpdateTvShowsQuestionCountUseCase,
    override val updateUserPointsUseCase: UpdateUserPointsUseCase,
    private val levelUpTvShowUseCase: LevelUpTvShowUseCase,
):BaseTriviaAnswerGameViewModel(state = GameUiState()) {
    override val gameType: GameType = GameType.TV_SHOW

    init {
        getData()
    }

    override val getQuestion: suspend () -> QuestionEntity
        get() = getCurrentTvShowQuestion::invoke

    override fun onSuccessUser(user: UserEntity) {
        _state.update {
            it.copy(
                level = user.tvShowGameLevel,
                points = user.totalPoints,
                questionCount = user.numTvShowQuestionsPassed + 1,
                isError = false,
                isLoading = false
            )
        }
    }

    override suspend fun levelUpUseCase() {
        levelUpTvShowUseCase()
    }

    override suspend fun updateQuestionCountUseCase(questionCount: Int) {
        updateTvShowsQuestionCountUseCase(questionCount)
    }
}
