package com.coderops.usecase


import com.coderops.entities.GenreEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetAllGenresMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<GenreEntity> {
        movieRepository.refreshGenres()
        return movieRepository.getGenresMovies().sortedBy { it.genreName }
    }
}