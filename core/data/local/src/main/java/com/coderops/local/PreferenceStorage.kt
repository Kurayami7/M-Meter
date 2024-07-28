package com.coderops.local

interface PreferenceStorage {
    val sessionId: String?
    val currentUserName: String?
    val accessToken: String?
    val lastRefreshTime: Long?

    suspend fun setSessionId(sessionId: String)
    suspend fun setCurrentUserName(currentUserName: String)
    suspend fun setAccessToken(accessToken: String)
    suspend fun setLastRefreshTime(lastRefreshTime: Long)
    suspend fun clearPreferenceStorage()
}
