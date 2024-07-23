package com.coderops.viewmodel.my_list_details.mapper

import com.coderops.entities.MovieEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.my_list_details.MovieUiState
import javax.inject.Inject

class MyListDetailsUiMapper @Inject constructor() : Mapper<MovieEntity, MovieUiState> {
    override fun map(input: MovieEntity): MovieUiState {
        return MovieUiState(
            id= input.id,
            title = input.title,
            imageUrl =input.imageUrl,
            genres = input.convertGenreListToString(),
            year = input.extractYearFromDate(),
            rating = input.rate,
            mediaType =input.mediaType,
        )
    }

    private fun MovieEntity.convertGenreListToString(): String {
        return genreEntities.joinToString(" | ") { it.genreName }
    }

    private fun MovieEntity.extractYearFromDate(): String {
        val parts = year.split("-")
        return parts[0]
    }
}