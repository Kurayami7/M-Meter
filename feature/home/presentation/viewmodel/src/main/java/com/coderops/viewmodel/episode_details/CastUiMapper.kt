package com.coderops.viewmodel.episode_details

import com.coderops.entities.PeopleEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.PeopleUIState
import javax.inject.Inject

class CastUiMapper @Inject constructor() : Mapper<PeopleEntity, PeopleUIState> {
    override fun map(input: PeopleEntity): PeopleUIState {
        return PeopleUIState(
            id = input.id,
            name = input.name,
            imageUrl = input.imageUrl
        )
    }
}