package com.coderops.repository.mappers.domain

import com.coderops.entities.YoutubeVideoDetailsEntity
import com.coderops.remote.response.dto.YoutubeVideoDetailsRemoteDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainYoutubeDetailsMapper @Inject constructor() :
    Mapper<YoutubeVideoDetailsRemoteDto, YoutubeVideoDetailsEntity> {
    override fun map(input: YoutubeVideoDetailsRemoteDto): YoutubeVideoDetailsEntity {
        return YoutubeVideoDetailsEntity(
            key = input.key ?: "",
            name = input.name ?: "",
            site = input.site ?: "",
            type = input.type ?: ""
        )
    }
}