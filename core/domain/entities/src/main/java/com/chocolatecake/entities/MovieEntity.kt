package com.chocolatecake.entities

data class MovieEntity(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val genreEntities: List<GenreEntity>,
    val rate: Double,
    val year: String = ""
){
    fun convertGenreListToString(): String {
        return genreEntities.joinToString(" | ") { it.genreName }
    }

    fun extractYearFromDate(): String {
        val parts = year.split("-")
        return parts[0]
    }
}