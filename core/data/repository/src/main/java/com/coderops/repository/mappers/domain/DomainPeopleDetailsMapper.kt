package com.coderops.repository.mappers.domain

import com.coderops.entities.PeopleDetailsEntity
import com.coderops.remote.response.dto.PeopleDetailsResponse
import com.coderops.repository.BuildConfig
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainPeopleDetailsMapper @Inject constructor() :
    Mapper<PeopleDetailsResponse, PeopleDetailsEntity> {
    override fun map(input: PeopleDetailsResponse): PeopleDetailsEntity {
        return PeopleDetailsEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            imageUrl = (BuildConfig.IMAGE_BASE_PATH + input.profilePath),
            placeOfBirth = input.placeOfBirth ?: "",
            gender = input.gender.toString(),
            acting = input.knownForDepartment.toString(),
            num_movies = "",
            biography = input.biography ?:""
        )
    }
}