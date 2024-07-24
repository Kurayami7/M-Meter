package com.coderops.viewmodel.search.mappers

import com.coderops.entities.PeopleEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.PeopleUIState
import javax.inject.Inject

class PeopleUiMapper @Inject constructor() :
    Mapper<PeopleEntity, PeopleUIState> {
    override fun map(input: PeopleEntity): PeopleUIState {
        return PeopleUIState(
            input.id,
            input.name,
            input.imageUrl
        )
    }
}