package com.coderops.viewmodel.movieDetails.mapper

import com.coderops.entities.UserListEntity
import com.coderops.mapper.Mapper
import com.coderops.viewmodel.common.model.UserListUi
import com.coderops.viewmodel.movieDetails.MovieDetailsUiState
import javax.inject.Inject

class UserListsUiMapper @Inject constructor() : Mapper<List<UserListEntity>, MovieDetailsUiState> {
    override fun map(input: List<UserListEntity>): MovieDetailsUiState {
        return MovieDetailsUiState(
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