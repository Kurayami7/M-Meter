package com.coderops.repository.mappers.domain

import com.coderops.entities.PeopleEntity
import com.coderops.remote.response.dto.PeopleRemoteDto
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainPeopleRemoteMapper @Inject constructor() : Mapper<PeopleRemoteDto, PeopleEntity> {
    override fun map(input: PeopleRemoteDto): PeopleEntity {
        return PeopleEntity(
            id =  input.id ?: 0,
            name = input.name ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.profilePath
        )
    }
}