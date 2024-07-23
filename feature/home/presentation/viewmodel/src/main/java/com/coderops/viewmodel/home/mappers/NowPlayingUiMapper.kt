package com.coderops.viewmodel.home.mappers

import com.coderops.entities.MovieEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.home.NowPlayingUiState
import javax.inject.Inject

class NowPlayingUiMapper @Inject constructor() : Mapper<MovieEntity, NowPlayingUiState> {
    override fun map(input: MovieEntity): NowPlayingUiState {
        return NowPlayingUiState(
            input.id,
            input.imageUrl
        )
    }
}