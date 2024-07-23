package com.coderops.viewmodel.search.mappers

import com.coderops.entities.GenreEntity
import com.coderops.entities.MovieEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.MovieHorizontalUIState
import javax.inject.Inject

class MovieUiMapper @Inject constructor()  : Mapper<MovieEntity, MovieHorizontalUIState> {
    override fun map(input: MovieEntity): MovieHorizontalUIState {
        return MovieHorizontalUIState(
            id = input.id,
            rate = input.rate,
            title = input.title,
            imageUrl = input.imageUrl,
            year = extractYearFromDate(input.year),
            genres = convertGenreListToString(input.genreEntities),
        )
    }

    private fun convertGenreListToString(list: List<GenreEntity>): String {
        return list.joinToString(" | ") { it.genreName }
    }

    private fun extractYearFromDate(year: String): String {
        val parts = year.split("-")
        return parts[0]
    }
}