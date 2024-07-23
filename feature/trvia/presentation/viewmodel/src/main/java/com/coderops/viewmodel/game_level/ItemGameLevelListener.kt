package com.coderops.viewmodel.game_level

import com.coderops.bases.BaseInteractionListener
import com.coderops.viewmodel.common.model.ItemGameLevelUIState

interface ItemGameLevelListener : BaseInteractionListener {
    fun onClickItem(itemGameLevelUIState: ItemGameLevelUIState)
}