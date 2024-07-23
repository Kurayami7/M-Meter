package com.coderops.viewmodel.home.mappers

import com.coderops.entities.MovieEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.home.UpComingMoviesUiState
import javax.inject.Inject

class UpComingUiMapper @Inject constructor() : Mapper<MovieEntity, UpComingMoviesUiState> {
    override fun map(input: MovieEntity): UpComingMoviesUiState {
        return UpComingMoviesUiState(
            input.id,
            input.imageUrl
        )
    }
}