package com.coderops.viewmodel.profile

import com.coderops.entities.ProfileEntity
import com.coderops.mapper.Mapper
import javax.inject.Inject

class ProfileUiMapper @Inject constructor() : Mapper<ProfileEntity, ProfileUIState> {
    override fun map(input: ProfileEntity): ProfileUIState {
        return ProfileUIState(
            input.username,
            input.avatarUrl
        )
    }
}