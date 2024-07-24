package com.coderops.viewmodel.showmore.mappers

import com.coderops.entities.GenreEntity
import com.coderops.entities.MovieEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.showmore.ShowMoreUi
import javax.inject.Inject

class ShowMoreUiMapper @Inject constructor() :
    Mapper<MovieEntity, ShowMoreUi> {
    override fun map(input: MovieEntity): ShowMoreUi {
        return ShowMoreUi(
            input.id,
            input.title,
            input.imageUrl,
            input.year,
            convertGenreListToString(input.genreEntities),
            input.rate,
        )
    }

    private fun convertGenreListToString(input: List<GenreEntity>): String {
        return input.joinToString(" | ") { it.genreName }
    }

}