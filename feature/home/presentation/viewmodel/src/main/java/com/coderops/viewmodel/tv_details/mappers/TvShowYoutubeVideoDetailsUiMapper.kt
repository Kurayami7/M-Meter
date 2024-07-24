package com.coderops.viewmodel.tv_details.mappers

import com.coderops.entities.YoutubeVideoDetailsEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.tv_details.TvDetailsUiState

class TvShowYoutubeVideoDetailsUiMapper : Mapper<YoutubeVideoDetailsEntity, TvDetailsUiState> {
    override fun map(input: YoutubeVideoDetailsEntity): TvDetailsUiState {
        return TvDetailsUiState(youtubeKeyId = input.key)
    }
}