package com.coderops.usecase.search

import com.coderops.entities.MovieEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,

) {
    suspend operator fun invoke(
        keyword: String,
        genreId: Int? = null
    ): List<MovieEntity> {

        return movieRepository.searchForMovies(keyword).filter { movie ->
            movie.genreEntities.takeIf { genreId != null }
                ?.map { it.genreID }
                ?.contains(genreId) ?: true && movie.rate != 0.0
        }.sortedByDescending { it.rate }
    }
}