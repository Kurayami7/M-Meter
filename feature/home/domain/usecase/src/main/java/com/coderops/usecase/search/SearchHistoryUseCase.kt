package com.coderops.usecase.search

import com.coderops.repository.MovieRepository
import javax.inject.Inject

class GetSearchHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): List<String> {
        return movieRepository.searchHistory()
    }
}