package com.coderops.viewmodel.tv_shows

import com.coderops.entities.TVShowsEntity
import com.coderops.mapper.Mapper

import javax.inject.Inject

class TVShowsMapper @Inject constructor() :
    Mapper<TVShowsEntity, TVShowsUI> {
    override fun map(input: TVShowsEntity): TVShowsUI {
        return TVShowsUI(
            tvId = input.id,
            imageUrl = input.imageUrl,
            rate = input.rate
        )
    }
}