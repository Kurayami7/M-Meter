package com.coderops.remote.service

import com.coderops.local.PreferenceStorage
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = preferenceStorage.accessToken
        val language = Locale.getDefault().language
        val request = chain.request()

        // Add OAuth 2.0 token to the Authorization header
        val requestBuilder = request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")

        // Add additional query parameters if needed
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("language", language)
            .build()

        return chain.proceed(requestBuilder.url(url).build())
    }
}
