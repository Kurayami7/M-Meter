package com.coderops.repository

import com.coderops.entities.ProfileEntity

interface AuthRepository {

    @Throws(exceptionClasses = [ApiThrowable::class])
    suspend fun login(username: String, password: String): Boolean

    suspend fun logout()
    suspend fun getCurrentUsername(): String?

    suspend fun getAccountDetails(): ProfileEntity
    suspend fun isUserLoggedIn():Boolean
}