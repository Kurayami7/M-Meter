package com.chocolatecake.viewmodel.search.mappers

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.viewmodel.search.SearchUiState
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