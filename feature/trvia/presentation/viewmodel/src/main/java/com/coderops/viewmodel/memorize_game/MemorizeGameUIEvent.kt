package com.coderops.viewmodel.memorize_game

import com.coderops.viewmodel.common.model.GameType

sealed interface MemorizeGameUIEvent{
    data class NavigateToWinnerScreen(val gameType: GameType): MemorizeGameUIEvent
    object NavigateToLoserScreen: MemorizeGameUIEvent
    object NavigateToWelcomeGameScreen: MemorizeGameUIEvent
    data class ShowSnackbar(val message: String): MemorizeGameUIEvent

}