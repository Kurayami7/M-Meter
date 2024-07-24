package com.coderops.viewmodel.game_level

import com.coderops.viewmodel.common.model.GameType
import com.coderops.viewmodel.common.model.ItemGameLevelUIState

data class GameLevelUIState(
    val gameLevel: List<ItemGameLevelUIState> = emptyList(),
    val gameType: GameType = GameType.PEOPLE,
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
