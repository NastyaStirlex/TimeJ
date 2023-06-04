package com.example.timej

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.example.timej.data.net.MainApiService
import com.example.timej.data.repository.JwtRepository
import com.example.timej.data.repository.UserAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class HiltModule {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = MainActivity().applicationContext

    @Provides
    @Singleton
    fun provideNavHostController(@ApplicationContext context: Context) =
        NavHostController(context = context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            navigatorProvider.addNavigator(DialogNavigator())
        }

    @Provides
    fun provideUserAuthRepository(
        @ApplicationContext context: Context,
        mainApiService: MainApiService,
        jwtRepository: JwtRepository
    ) =
        UserAuthRepository(mainApiService = mainApiService, jwtRepository = jwtRepository, context)
}