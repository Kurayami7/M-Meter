package com.coderops.viewmodel.search.mappers

import com.coderops.entities.TvEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.MovieHorizontalUIState
import javax.inject.Inject

class TvUiMapper @Inject constructor()  : Mapper<TvEntity, MovieHorizontalUIState> {
    override fun map(input: TvEntity): MovieHorizontalUIState {
        return MovieHorizontalUIState(
            id = input.id,
            rate = input.rate,
            title = input.title,
            imageUrl = input.imageUrl,
            year = input.extractYearFromDate(),
            genres = input.convertGenreListToString()
        )
    }
}