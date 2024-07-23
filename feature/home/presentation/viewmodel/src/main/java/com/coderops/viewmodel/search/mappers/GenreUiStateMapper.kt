package com.coderops.viewmodel.search.mappers

import com.coderops.entities.GenreEntity
import com.coderops.viewmodel.search.SearchUiState
import javax.inject.Inject

class GenreUiStateMapper @Inject constructor() {
    fun map(input: GenreEntity, isSelected: Boolean): SearchUiState.GenresUiState {
        return SearchUiState.GenresUiState(
            genreId = input.genreID,
            genresName = input.genreName,
            isSelected = isSelected
        )
    }
}