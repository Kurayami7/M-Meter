package com.coderops.viewmodel.myList.mapper

import com.coderops.entities.myList.ListCreatedEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.myList.ListMovieUiState
import javax.inject.Inject

class MyListUiMapper @Inject constructor() : Mapper<ListCreatedEntity, ListMovieUiState> {
    override fun map(input: ListCreatedEntity): ListMovieUiState {
        return ListMovieUiState(
            id = input.id ?: 0,
            itemCount = input.itemCount,
            listType = input.listType,
            name = input.name,
            posterPath = input.posterPath,
        )
    }
}