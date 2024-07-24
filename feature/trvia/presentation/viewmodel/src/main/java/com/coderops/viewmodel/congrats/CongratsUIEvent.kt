package com.coderops.viewmodel.congrats

import com.coderops.viewmodel.common.model.GameType

sealed interface CongratsUIEvent {
    data class NavigateToNextLevel(val gameType: GameType): CongratsUIEvent
    object NavigateToGameTypeScreen : CongratsUIEvent
}