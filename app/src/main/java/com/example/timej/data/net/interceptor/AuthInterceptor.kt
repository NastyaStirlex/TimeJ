package com.example.timej.data.net.interceptor

import com.example.timej.data.repository.DataStoreRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        if (request.header("Authorization") == null) {
            builder.addHeader(
                "Authorization",
                "Bearer ${dataStoreRepository.getAccessToken()}"
            )
        }

        return chain.proceed(builder.build())
    }

}