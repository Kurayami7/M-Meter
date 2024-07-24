package com.coderops.entities.myList



data class WatchlistRequestEntity(
    val watchlist: Boolean?,
    val mediaId: Int?,
    val mediaType: String,
)