package com.coderops.ui.game_level

import com.coderops.bases.BaseAdapter
import com.coderops.ui.trivia.R
import com.coderops.viewmodel.common.model.ItemGameLevelUIState
import com.coderops.viewmodel.game_level.ItemGameLevelListener

class GameLevelAdapter(
    items: List<ItemGameLevelUIState>,
    listener: ItemGameLevelListener
) : BaseAdapter<ItemGameLevelUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_card_level
}