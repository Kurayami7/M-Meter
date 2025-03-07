package com.coderops.usecase.search_history

import com.coderops.repository.MovieRepository
import javax.inject.Inject

class SearchHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(keyword: String): List<String> {
        return movieRepository.getSearchHistory(keyword = keyword).sorted()
    }
}