package com.coderops.viewmodel.tv_details.mappers

import com.coderops.entities.StatusEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvRatingUiMapper @Inject constructor() : Mapper<StatusEntity, TvDetailsUiState> {
    override fun map(input: StatusEntity): TvDetailsUiState {
        return TvDetailsUiState(
            ratingSuccess = input.statusMessage
        )
    }

}