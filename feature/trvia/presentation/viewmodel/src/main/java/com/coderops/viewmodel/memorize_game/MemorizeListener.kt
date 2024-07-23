package com.coderops.viewmodel.memorize_game

import com.coderops.bases.BaseInteractionListener

interface MemorizeListener : BaseInteractionListener {
    fun onItemClick(itemGameImageUiState: ItemGameImageUiState)
}