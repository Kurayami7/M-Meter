package com.coderops.usecase.search_history

import com.coderops.repository.MovieRepository
import javax.inject.Inject

class InsertSearchHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(keyword: String){
        return movieRepository.insertSearchHistory(keyword)
    }
}