package com.coderops.remote.request

import com.google.gson.annotations.SerializedName

data class RatingEpisodeDetailsRequest(
    @SerializedName("value")
    val value: Float
)
