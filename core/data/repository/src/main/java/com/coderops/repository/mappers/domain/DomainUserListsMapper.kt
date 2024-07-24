package com.coderops.repository.mappers.domain

import com.coderops.entities.UserListEntity
import com.coderops.remote.response.dto.UserListRemoteDto
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainUserListsMapper @Inject constructor() : Mapper<UserListRemoteDto, UserListEntity> {
    override fun map(input: UserListRemoteDto): UserListEntity {
        return UserListEntity(
            id = input.id ?: 0,
            name = input.name ?: ""
        )
    }
}