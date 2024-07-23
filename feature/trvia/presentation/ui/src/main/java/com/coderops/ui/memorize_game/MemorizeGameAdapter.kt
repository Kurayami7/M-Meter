package com.coderops.ui.memorize_game

import com.coderops.bases.BaseAdapter
import com.coderops.ui.trivia.R
import com.coderops.viewmodel.memorize_game.ItemGameImageUiState
import com.coderops.viewmodel.memorize_game.MemorizeListener

class MemorizeGameAdapter(
    private val list: List<ItemGameImageUiState>,
    private val listener: MemorizeListener
) : BaseAdapter<ItemGameImageUiState>(list, listener) {
    override val layoutID: Int = R.layout.item_memorize
}