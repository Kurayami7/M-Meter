package com.coderops.usecase.movie_details

import com.coderops.entities.StatusEntity
import com.coderops.repository.MovieRepository
import javax.inject.Inject

class AddToWatchList @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId:Int,mediaType:String, isWatchlist: Boolean=true): StatusEntity {
        return movieRepository.addWatchlist(movieId,mediaType,isWatchlist)
    }
}