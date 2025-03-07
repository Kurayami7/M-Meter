package com.coderops.remote.response


import com.google.gson.annotations.SerializedName

data class GenresWrapperResponse<T>(
    @SerializedName("genres")
    val results: List<T>?
)