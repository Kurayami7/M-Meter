package com.coderops.remote.response.dto.episode_details

import com.google.gson.annotations.SerializedName

data class EpisodeDetailsCastRemoteDto(
    @SerializedName("cast")
    val cast: List<CastRemoteDto>?,
    @SerializedName("id")
    val id: Int?
) {
    data class CastRemoteDto(
        @SerializedName("adult")
        val adult: Boolean?,
        @SerializedName("character")
        val character: String?,
        @SerializedName("credit_id")
        val creditId: String?,
        @SerializedName("gender")
        val gender: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("known_for_department")
        val knownForDepartment: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("order")
        val order: Int?,
        @SerializedName("original_name")
        val originalName: String?,
        @SerializedName("popularity")
        val popularity: Double?,
        @SerializedName("profile_path")
        val profilePath: String?
    )
}
