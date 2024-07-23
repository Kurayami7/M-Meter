package com.coderops.repository.mappers.domain

import com.coderops.entities.PeopleEntity
import com.coderops.local.database.dto.PopularPeopleLocalDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainPeopleMapper @Inject constructor() : Mapper<PopularPeopleLocalDto, PeopleEntity> {
    override fun map(input: PopularPeopleLocalDto): PeopleEntity {
        return PeopleEntity(
            id =  input.id,
            name = input.name,
            imageUrl = input.imagerUrl,
            popularity = input.popularity
        )
    }
}