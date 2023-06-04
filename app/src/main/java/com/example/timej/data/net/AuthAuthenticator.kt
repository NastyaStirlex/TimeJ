//package com.example.timej.data.di
//
//import com.example.timej.data.di.auth_interceptor.TokenManager
//import com.example.timej.data.dto.RefreshBodyDto
//import com.example.timej.data.dto.TokenDto
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.runBlocking
//import okhttp3.*
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Inject
//
//class AuthAuthenticator @Inject constructor(
//    private val tokenManager: TokenManager
//) : Authenticator {
//    override fun authenticate(route: Route?, response: Response): Request? {
//        val accessToken = runBlocking {
//            tokenManager.getAccessToken.first()
//        }
//        val refreshToken = runBlocking {
//            tokenManager.getRefreshToken().first()
//        }
//
//        return runBlocking {
//            val newTokens = getNewToken(accessToken = accessToken, refreshToken = refreshToken)
//
//            if (!newTokens.isSuccessful || newTokens.body() == null) { //Couldn't refresh the token, so restart the login process
//                tokenManager.deleteAccessToken()
//                tokenManager.deleteRefreshToken()
//            }
//
//            newTokens.body()?.let {
//                tokenManager.saveAccessToken(it.accessToken)
//                tokenManager.saveRefreshToken(it.refreshToken)
//                response.request.newBuilder()
//                    .header("Authorization", "Bearer ${it.accessToken}")
//                    .build()
//            }
//        }
//    }
//
//    private suspend fun getNewToken(
//        accessToken: String?,
//        refreshToken: String?
//    ): retrofit2.Response<TokenDto> {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://dev.api.k8s.pujak.ru/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//        val service = retrofit.create(AuthApiService::class.java)
//        return service.refresh(
//            RefreshBodyDto(
//                accessToken = accessToken,
//                refreshToken = refreshToken
//            )
//        )
//    }
//}