package com.coderops.viewmodel.home.mappers

import com.coderops.entities.PeopleEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.home.PopularPeopleUiState
import javax.inject.Inject

class PopularPeopleUiMapper @Inject constructor() :
    Mapper<PeopleEntity, PopularPeopleUiState> {
    override fun map(input: PeopleEntity): PopularPeopleUiState {
        return PopularPeopleUiState(
            input.id,
            input.imageUrl,
            input.name
        )
    }
}