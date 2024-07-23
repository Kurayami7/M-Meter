package com.coderops.viewmodel.home.mappers

import com.coderops.entities.MovieEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.home.TrendingMoviesUiState
import javax.inject.Inject

class TrendingUiMapper @Inject constructor()  : Mapper<MovieEntity, TrendingMoviesUiState> {
    override fun map(input: MovieEntity): TrendingMoviesUiState {
        return TrendingMoviesUiState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}