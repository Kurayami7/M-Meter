package com.coderops.remote.service

import com.coderops.local.PreferenceStorage
import com.coderops.remote.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val preferenceStorage: PreferenceStorage,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val language = Locale.getDefault().language
        val apiKey = BuildConfig.API_KEY
        val sessionId = preferenceStorage.sessionId

        val request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .addQueryParameter(SESSION_ID, sessionId)
            .addQueryParameter(LANGUAGE, language)
            .build()

        return chain.proceed(request.newBuilder().url(url).build())
    }

    private companion object {
        const val API_KEY = "api_key"
        const val SESSION_ID = "session_id"
        const val LANGUAGE = "language"
    }
}