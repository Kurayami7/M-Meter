package com.coderops.viewmodel.tv_details.mappers

import com.coderops.entities.PeopleEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.PeopleUIState
import com.coderops.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvDetailsCastUiMapper @Inject constructor() :
    Mapper<List<PeopleEntity>, TvDetailsUiState> {

    override fun map(input: List<PeopleEntity>): TvDetailsUiState {
        return TvDetailsUiState(
            cast = mapCastToUi(input)
        )
    }

    private fun mapCastToUi(castEntity: List<PeopleEntity>): List<PeopleUIState> {
        return castEntity.map {
            PeopleUIState(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl
            )
        }
    }


}