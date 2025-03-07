package com.coderops.remote.response.dto.season_details

import com.google.gson.annotations.SerializedName

data class EpisodeDto (
    @SerializedName("air_date")
    val airDate: String? = null,
    @SerializedName("crew")
    val crew: List<Crew?>? = null,
    @SerializedName("episode_number")
    val episodeNumber: Int? = null,
    @SerializedName("guest_stars")
    val guestStars: List<GuestStar?>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("production_code")
    val productionCode: String? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("season_number")
    val seasonNumber: Int? = null,
    @SerializedName("show_id")
    val showId: Int? = null,
    @SerializedName("still_path")
    val stillPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
        )