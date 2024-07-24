package com.coderops.viewmodel.episode_details

import com.coderops.entities.YoutubeVideoDetailsEntity
import com.coderops.mapper.Mapper
import javax.inject.Inject

class TrailerUiMapper @Inject constructor() : Mapper<YoutubeVideoDetailsEntity, TrailerUiState> {
    override fun map(input: YoutubeVideoDetailsEntity): TrailerUiState {
        return TrailerUiState(
            videoKey = input.key
        )
    }
}