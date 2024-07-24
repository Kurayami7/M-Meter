package com.coderops.viewmodel.people.mapper

import com.coderops.entities.TvShowEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.people.PersonDetailsUiState
import javax.inject.Inject

class TvShowsByPeopleUiMapper @Inject constructor() :  Mapper<TvShowEntity, PersonDetailsUiState.PeopleMediaUiState> {
    override fun map(input: TvShowEntity): PersonDetailsUiState.PeopleMediaUiState {
        return PersonDetailsUiState.PeopleMediaUiState(
            id = input.id,
            name = "tvShows",
            imageUrl = input.imageUrl,
            rate = input.rate
        )
    }
}