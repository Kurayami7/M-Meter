package com.coderops.repository.mappers.domain

import com.coderops.entities.ProfileEntity
import com.coderops.remote.response.dto.profile.ProfileRemoteDto
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainProfileMapper @Inject constructor(): Mapper<ProfileRemoteDto, ProfileEntity> {
    override fun map(input: ProfileRemoteDto): ProfileEntity {
        return ProfileEntity(
            username = "@" + input.username,
            avatarUrl = BuildConfig.IMAGE_BASE_PATH + input.avatar?.tmdb?.avatarPath
        )
    }
}