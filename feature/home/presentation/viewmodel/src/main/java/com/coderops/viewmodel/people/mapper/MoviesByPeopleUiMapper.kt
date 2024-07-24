package com.coderops.viewmodel.people.mapper

import com.coderops.entities.MovieEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.people.PersonDetailsUiState
import javax.inject.Inject

class MoviesByPeopleUiMapper @Inject constructor() :
    Mapper<MovieEntity, PersonDetailsUiState.PeopleMediaUiState> {
    override fun map(input: MovieEntity): PersonDetailsUiState.PeopleMediaUiState {
        return PersonDetailsUiState.PeopleMediaUiState(
            id = input.id,
            name = "movies",
            imageUrl = input.imageUrl,
            rate = input.rate
        )
    }
}