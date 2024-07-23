package com.coderops.remote.request

import com.google.gson.annotations.SerializedName

data class RatingRequest(
    @SerializedName("value")
    val value: Float
)