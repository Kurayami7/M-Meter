package com.coderops.viewmodel.movieDetails.mapper

import com.coderops.entities.myList.FavoriteBodyRequestEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.movieDetails.FavoriteBodyUiState
import javax.inject.Inject

class FavoriteBodyUiMapper @Inject constructor() :
    Mapper<FavoriteBodyRequestEntity, FavoriteBodyUiState> {
    override fun map(input: FavoriteBodyRequestEntity): FavoriteBodyUiState {
        return FavoriteBodyUiState(
            favorite = input.favorite,
            mediaId = input.mediaId,
            mediaType = input.mediaType,
        )
    }

}