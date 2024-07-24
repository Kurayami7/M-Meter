package com.coderops.usecase.movie_details

import com.coderops.repository.MovieRepository
import javax.inject.Inject

class CheckIsLoginedOrNotUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Boolean {
        return movieRepository.isLoginedOrNot()
    }
}