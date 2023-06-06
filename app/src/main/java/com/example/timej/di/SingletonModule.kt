package com.example.timej.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.timej.data.net.MainApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

//    @Singleton
//    @Provides
//    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

//    @Singleton
//    @Provides
//    fun provideOkHttpClient(
//        authInterceptor: AuthInterceptor,
//        authAuthenticator: AuthAuthenticator,
//    ): OkHttpClient {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        return OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
//            .addInterceptor(loggingInterceptor)
//            .authenticator(authAuthenticator)
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor =
//        AuthInterceptor(tokenManager)

    //    @Singleton
//    @Provides
//    fun provideAuthAuthenticator(tokenManager: TokenManager): AuthAuthenticator =
//        AuthAuthenticator(tokenManager)
    @Provides
    fun interceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dev.api.k8s.pujak.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor())
                    .build()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMainApiService(): MainApiService =
        retrofit
            .create(MainApiService::class.java)

    private val retrofit: Retrofit = provideRetrofitBuilder()

//    @Provides
//    @Singleton
//    fun provideAuthApiService(retrofit: Retrofit.Builder): AuthApiService =
//        retrofit
//            .build()
//            .create(AuthApiService::class.java)
}