package com.coderops.viewmodel.season_details

import com.coderops.viewmodel.common.model.EpisodeHorizontalUIState

data class SeasonDetailsUiState(
    val id : Int = 0,
    val name : String = "",
    val overview : String = "",
    val episodes : List<EpisodeHorizontalUIState> = emptyList(),
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = false,
)
