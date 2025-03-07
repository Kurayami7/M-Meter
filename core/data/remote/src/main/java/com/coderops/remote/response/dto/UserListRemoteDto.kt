package com.coderops.remote.response.dto


import com.google.gson.annotations.SerializedName

data class UserListRemoteDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
)