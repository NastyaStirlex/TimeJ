package com.example.timej.data.repository

import android.content.Context
import androidx.compose.runtime.MutableState
import com.example.timej.R
import com.example.timej.data.net.MainApiService
import com.example.timej.data.dto.LoginBodyDto
import com.example.timej.data.net.Event
import com.example.timej.data.dto.TokenDto
import com.example.timej.data.dto.UserDto
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

class UserAuthRepository @Inject constructor(
    private val mainApiService: MainApiService,
    private val jwtRepository: JwtRepository,
    @ApplicationContext application: Context
) {
    val context = application

    val accessToken = jwtRepository.getAccessToken(context = context)
    val role = jwtRepository.getUserRole(context = context)
    var email = jwtRepository.getEmail(context = context)
    var password = jwtRepository.getPassword(context = context)

    fun saveEmail(email: String) = jwtRepository.saveEmail(context = context, email = email)
    fun savePassword(password: String) = jwtRepository.savePassword(context = context, password = password)

    val groupId = jwtRepository.getGroupId(context = context)
    val groupNumber = jwtRepository.getGroupNumber(context = context)
    val studentName = jwtRepository.getStudentName(context = context)

    val teacherName = jwtRepository.getTeacherName(context = context)
    val teacherId = jwtRepository.getTeacherId(context = context)

    suspend fun getUserDetails(accessToken: String): UserDto {
        return mainApiService.getUserDetails(accessToken = "Bearer $accessToken")

    }

    // LOGIN
    suspend fun login(
        loginBody: LoginBodyDto,
        signInScreenState: MutableState<Event<TokenDto>>
    ) {
        try {
            signInScreenState.value = Event.loading()

            val response = mainApiService.login(loginBody = loginBody)

            if (response.isSuccessful) {
                response.body()?.accessToken?.let {
                    jwtRepository.saveAccessToken(
                        context = context,
                        accessToken = it
                    )
                }
                response.body()?.refreshToken?.let {
                    jwtRepository.saveRefreshToken(
                        context = context,
                        refreshToken = it
                    )
                }

                signInScreenState.value = Event.success(response.body())
            } else if (response.errorBody() != null) {
                if (response.code() == 400)
                    signInScreenState.value = Event.error(R.string.login_failed)
            }


        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    signInScreenState.value = Event.error(R.string.http_error)
                }
                is UnknownHostException, is SocketException -> {
                    signInScreenState.value = Event.error(R.string.unknown_error)
                }
            }
            e.printStackTrace()
            signInScreenState.value = Event.error(error = null)
        }
    }

    // LOGOUT

    suspend fun logout(profileScreenState: MutableState<Event<String>>) {
        try {
            profileScreenState.value = Event.loading()

            mainApiService.logout(accessToken = "Bearer $accessToken")

            //email = jwtRepository.getEmail(context = context)
            //password = jwtRepository.getPassword(context = context)

            jwtRepository.deleteAccessToken(context = context)
            jwtRepository.deleteRefreshToken(context = context)
            jwtRepository.deleteUserRole(context = context)
            jwtRepository.deleteGroupId(context = context)
            jwtRepository.deleteGroupNumber(context = context)
            jwtRepository.deleteStudentName(context = context)
            jwtRepository.deleteTeacherName(context = context)
            jwtRepository.deleteTeacherId(context = context)

            profileScreenState.value = Event.success(null)

        } catch (e: java.lang.Exception) {
            when (e) {
                is HttpException -> {
                    profileScreenState.value = Event.error(R.string.http_error)
                }
                is UnknownHostException, is SocketException -> {
                    profileScreenState.value = Event.error(R.string.unknown_error)
                }
            }
            e.printStackTrace()
            profileScreenState.value = Event.error(error = null)
        }
    }

    fun isAuthenticated() = accessToken != null

    fun isStudent() = role == "STUDENT"

    fun isTeacher() = role == "TEACHER"

//    // REFRESH
//    suspend fun refreshToken(accessToken: String, refreshToken: String): String {
//        try {
//            val response = authApiService.refresh(
//                refreshBody = RefreshBodyDto(
//                    accessToken = accessToken,
//                    refreshToken = refreshToken
//                )
//            )
//
//            if (response.isSuccessful) {
//                return response.body()?.refreshToken ?: ""
//                //signInScreenState.value = Event.success(response.body())
//            } else if (response.errorBody() != null) {
//                if (response.code() == 400)
//                    Log.d("","")
//                    //signInScreenState.value = Event.error(R.string.login_failed)
//            }
//        } catch (e: Exception) {}
//
//        return ""
//    }
}