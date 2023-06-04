package com.example.timej.data.net.auth_interceptor

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getAccessToken.first()
        }
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $token")

        return chain.proceed(request = request.build())

//        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
//            val newAccessToken = tokenManager.getAccessToken()
//            if (newAccessToken != accessToken) {
//                return chain.proceed(newRequestWithAccessToken(accessToken, request))
//            } else {
//                accessToken = refreshToken()
//                if (accessToken.isNullOrBlank()) {
//                    tokenManager.logout()
//                    return response
//                }
//                return chain.proceed(newRequestWithAccessToken(accessToken, request))
//            }
//        }

    }

//    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
//        request.newBuilder()
//            .header("Authorization", "Bearer $accessToken")
//            .build()
//
//    private fun refreshToken(): String? {
//        synchronized(this) {
//            val accessToken = tokenManager.getAccessToken()
//            val refreshToken = tokenManager.getRefreshToken()
//            refreshToken?.let {
//                return accessToken?.let { it1 -> tokenManager.refreshToken(it1, refreshToken) }
//            } ?: return null
//        }
//    }
}