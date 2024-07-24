package com.coderops.viewmodel.tv_details.mappers

import com.coderops.entities.TvShowEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.MediaVerticalUIState
import com.coderops.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvShowUiMapper @Inject constructor() : Mapper<List<TvShowEntity>, TvDetailsUiState> {
    override fun map(input: List<TvShowEntity>): TvDetailsUiState {
        return TvDetailsUiState(
            recommended = input.map { tvShow ->
                MediaVerticalUIState(
                    id = tvShow.id,
                    imageUrl = tvShow.imageUrl,
                    rate = tvShow.rate
                )
            }
        )
    }
}