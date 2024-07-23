package com.coderops.viewmodel.episode_details

import com.coderops.entities.EpisodeDetailsEntity
import com.coderops.mapper.Mapper
import javax.inject.Inject

class EpisodeDetailsUiMapper  @Inject constructor() : Mapper<EpisodeDetailsEntity,EpisodeDetailsUiState> {
    override fun map(input: EpisodeDetailsEntity): EpisodeDetailsUiState {
        return EpisodeDetailsUiState(
            imageUrl = input.imageUrl,
            episodeNumber = input.episodeNumber,
            episodeName = input.episodeName,
            episodeRate = input.episodeRate,
            episodeOverview = input.overview,
            seasonNumber = input.seasonNumber,
            voteAverage = input.voteAverage
        )
    }
}