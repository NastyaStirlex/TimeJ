package com.example.timej.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.timej.data.net.MainApiService
import com.example.timej.data.repository.DataStoreRepository
import com.example.timej.data.repository.JwtRepository
import com.example.timej.data.repository.ScheduleRepository
import com.example.timej.data.repository.UserAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideUserAuthRepository(
        @ApplicationContext context: Context,
        mainApiService: MainApiService,
        jwtRepository: JwtRepository
    ) =
        UserAuthRepository(mainApiService = mainApiService, jwtRepository = jwtRepository, context)

    @Singleton
    @Provides
    fun provideScheduleRepository(
        mainApiService: MainApiService
    ) =
        ScheduleRepository(mainApiService = mainApiService)

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        dataStore: DataStore<Preferences>
    ) =
        DataStoreRepository(dataStore)
}