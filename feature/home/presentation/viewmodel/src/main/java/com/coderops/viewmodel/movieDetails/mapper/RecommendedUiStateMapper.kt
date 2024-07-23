package com.coderops.viewmodel.movieDetails.mapper

import com.coderops.entities.movieDetails.RecommendedMovieEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.MediaVerticalUIState
import javax.inject.Inject

class RecommendedUiStateMapper @Inject constructor() :
    Mapper<RecommendedMovieEntity, MediaVerticalUIState> {
    override fun map(input: RecommendedMovieEntity): MediaVerticalUIState {
        return MediaVerticalUIState(
            id = input.id,
            rate = input.voteAverage,
            imageUrl = input.posterPath,
        )
    }

}