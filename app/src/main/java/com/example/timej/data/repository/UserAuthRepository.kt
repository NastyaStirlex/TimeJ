package com.example.timej.data.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.timej.R
import com.example.timej.data.callbacks.GetUserDetailsCallback
import com.example.timej.data.net.MainApiService
import com.example.timej.data.dto.LoginBodyDto
import com.example.timej.data_classes.Event
import com.example.timej.data.dto.TokenDto
import com.example.timej.data.dto.UserDto
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
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

    private var callGetUser: Call<UserDto>? = null

    fun getUserDetails(accessToken: String, callback: GetUserDetailsCallback<UserDto>) {
        callGetUser = mainApiService.getUserDetails(accessToken = "Bearer $accessToken")

        callGetUser?.enqueue(
            object : Callback<UserDto> {
                override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }

                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                userDetails = it
                            )
                            jwtRepository.saveUserRole(response.body()!!.roles[0], context)
                            when (response.body()!!.roles[0]) {
                                "STUDENT" -> {
                                    Log.d("save role: ", "student")
                                    jwtRepository.saveGroupId(response.body()!!.group.id, context)
                                    Log.d("save group id: ", response.body()!!.group.id)
                                    jwtRepository.saveGroupNumber(
                                        response.body()!!.group.groupNumber.toString(),
                                        context
                                    )
                                    Log.d(
                                        "save group number: ",
                                        response.body()!!.group.groupNumber.toString()
                                    )
                                    jwtRepository.saveStudentName(
                                        name = response.body()!!.surname + " " + response.body()!!.name + " " + response.body()!!.middleName,
                                        context = context
                                    )

                                    Log.d(
                                        "save student name: ",
                                        response.body()!!.surname + " " + response.body()!!.name + " " + response.body()!!.middleName
                                    )
                                }
                                "TEACHER" -> {
                                    Log.d("save role: ", "teacher")

                                    val fullname =
                                        "${response.body()!!.surname} ${response.body()!!.name} ${response.body()!!.middleName}"
                                    jwtRepository.saveTeacherName(
                                        name = fullname,
                                        context = context
                                    )
                                    jwtRepository.saveTeacherId(
                                        id = response.body()!!.id,
                                        context = context
                                    )

                                    Log.d("save teacher name and id: ", fullname)
                                }
                                else -> {
                                    Log.d(
                                        "not student and teacher!!!",
                                        response.body()!!.roles.joinToString(", ")
                                    )
                                }
                            }
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                }
                            }
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")

                            return
                        }
                    }
                }

                override fun onFailure(call: Call<UserDto>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
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