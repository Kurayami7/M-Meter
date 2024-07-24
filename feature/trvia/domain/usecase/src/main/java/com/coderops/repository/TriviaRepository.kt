package com.coderops.repository

import com.coderops.entities.BoardEntity
import com.coderops.entities.QuestionEntity
import com.coderops.entities.TVShowsEntity
import com.coderops.entities.UserEntity

interface TriviaRepository {
    /// region user
    suspend fun getUserByUsername(username: String): UserEntity
    suspend fun deleteUserByUsername(username: String)
    suspend fun addUserByUsername(username: String)
    suspend fun updateUser(userEntity: UserEntity)
    /// endregion

    /// region game
    suspend fun getPeopleQuestion(level: Int, questionNumber: Int): QuestionEntity
    suspend fun getMovieQuestion(level: Int, questionNumber: Int): QuestionEntity

    suspend fun getPopularTvShows(): List<TVShowsEntity>
    suspend fun getTvShowQuestion(level: Int, questionNumber: Int): QuestionEntity
    suspend fun getBoard(level: Int): BoardEntity
    /// endregion
}