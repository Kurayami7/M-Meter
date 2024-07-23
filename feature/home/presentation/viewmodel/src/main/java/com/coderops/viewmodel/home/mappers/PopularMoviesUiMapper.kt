package com.coderops.viewmodel.home.mappers

import com.coderops.entities.MovieEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.home.PopularMoviesUiState
import javax.inject.Inject

class PopularMoviesUiMapper @Inject constructor() :
    Mapper<MovieEntity, PopularMoviesUiState> {
    override fun map(input: MovieEntity): PopularMoviesUiState {
        return PopularMoviesUiState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}