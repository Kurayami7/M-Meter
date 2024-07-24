package com.coderops.viewmodel.movieDetails.mapper

import com.coderops.entities.movieDetails.CastEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.PeopleUIState
import javax.inject.Inject

class CastUiStateMapper@Inject constructor() :
    Mapper<CastEntity, PeopleUIState> {
    override fun map(input: CastEntity): PeopleUIState {
        return PeopleUIState(
            id = input.id,
            name = input.name,
            imageUrl = input.profilePath
        )
    }

}