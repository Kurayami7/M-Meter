package com.coderops.viewmodel.tv_details.mappers

import com.coderops.entities.SeasonEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.SeasonHorizontalUIState
import com.coderops.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvDetailsSeasonUiMapper @Inject constructor() : Mapper<List<SeasonEntity>, TvDetailsUiState> {
    override fun map(input: List<SeasonEntity>): TvDetailsUiState {
        return TvDetailsUiState(
            seasons = input.map { season ->
                SeasonHorizontalUIState(
                    id = season.id,
                    imageUrl = season.imageUrl,
                    title = season.title,
                    description = season.description,
                    year = extractYearFromDate(season.year),
                    countEpisode = season.countEpisode,
                    seasonNumber =  season.seasonNumber
                )
            }
        )
    }

    private fun extractYearFromDate(year: String): String {
        val parts = year.split("-")
        return parts[0]
    }

}