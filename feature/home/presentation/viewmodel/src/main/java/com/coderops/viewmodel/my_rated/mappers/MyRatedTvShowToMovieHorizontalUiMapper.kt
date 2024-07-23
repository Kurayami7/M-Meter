package com.coderops.viewmodel.my_rated.mappers

import com.coderops.entities.my_rated.MyRatedTvShowEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.MovieHorizontalUIState
import javax.inject.Inject

class MyRatedTvShowToMovieHorizontalUiMapper @Inject constructor()  : Mapper<MyRatedTvShowEntity, MovieHorizontalUIState> {
    override fun map(input: MyRatedTvShowEntity): MovieHorizontalUIState {
        return MovieHorizontalUIState(
            id = input.id,
            rate = input.rate,
            title = input.title,
            imageUrl = input.imageUrl,
            year = extractYearFromDate(input.year),
            genres = convertGenreListToString(input.genreEntities.map { it.genreName }),
        )
    }

    private fun convertGenreListToString(list: List<String>): String {
        return list.joinToString(" | ")
    }

    private fun extractYearFromDate(year: String): String {
        val parts = year.split("-")
        return parts[0]
    }
}