package com.coderops.viewmodel.tv_details.mappers

import com.coderops.entities.UserListEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.UserListUi
import com.coderops.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class UserListsUiMapper @Inject constructor() : Mapper<List<UserListEntity>, TvDetailsUiState> {
    override fun map(input: List<UserListEntity>): TvDetailsUiState {
        return TvDetailsUiState(
            userLists = input.map(::mapUserListToUi)
        )
    }

    private fun mapUserListToUi(userListEntity: UserListEntity): UserListUi {
        return UserListUi(
            id = userListEntity.id,
            name = userListEntity.name
        )
    }
}