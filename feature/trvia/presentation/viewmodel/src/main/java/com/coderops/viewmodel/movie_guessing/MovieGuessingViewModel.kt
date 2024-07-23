package com.coderops.viewmodel.movie_guessing

import com.coderops.entities.QuestionEntity
import com.coderops.entities.UserEntity
import com.coderops.usecase.GetCurrentUserUseCase
import com.coderops.usecase.game.UpdateUserPointsUseCase
import com.coderops.usecase.game.levelup.LevelUpMoviesUseCase
import com.coderops.usecase.game.questions.GetCurrentMovieQuestion
import com.coderops.usecase.game.questions.UpdateMoviesQuestionCountUseCase
import com.coderops.viewmodel.common.BaseTriviaAnswerGameViewModel
import com.coderops.viewmodel.common.model.GameType
import com.coderops.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieGuessingViewModel @Inject constructor(
    override val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentMovieQuestion: GetCurrentMovieQuestion,
    private val updateMovieQuestionCountUseCase: UpdateMoviesQuestionCountUseCase,
    override val updateUserPointsUseCase: UpdateUserPointsUseCase,
    private val levelUpMovieUseCase: LevelUpMoviesUseCase,
) : BaseTriviaAnswerGameViewModel(state = GameUiState()) {
    override val gameType = GameType.MOVIE
    init {
        getData()
    }

    override val getQuestion: suspend () -> QuestionEntity
        get() = getCurrentMovieQuestion::invoke

    override fun onSuccessUser(user: UserEntity) {
        _state.update {
            it.copy(
                level = user.moviesGameLevel,
                points = user.totalPoints,
                questionCount = user.numMoviesQuestionsPassed + 1,
                isError = false,
                isLoading = false
            )
        }
    }

    override suspend fun levelUpUseCase() {
        levelUpMovieUseCase()
    }

    override suspend fun updateQuestionCountUseCase(questionCount: Int) {
        updateMovieQuestionCountUseCase(questionCount)
    }

}