package com.coderops.repository.mappers.domain

import com.coderops.entities.StatusEntity
import com.coderops.remote.response.dto.StatusResponse
import com.coderops.repository.mappers.Mapper
import javax.inject.Inject

class DomainStatusMapper @Inject constructor() : Mapper<StatusResponse, StatusEntity> {
    override fun map(input: StatusResponse): StatusEntity {
        return StatusEntity(
            statusCode = input.statusCode?:0,
            statusMessage = input.statusMessage?:"",
            success = input.success?:false
        )
    }
}