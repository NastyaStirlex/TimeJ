package com.example.timej.data.net

import com.example.timej.data.dto.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MainApiService {

    @GET("api/building/all")
    fun getBuildings(): Call<List<BuildingDto>>

    @GET("api/building/{buildingId}/auditory/all")
    fun getAuditoriums(@Path("buildingId") buildingId: String): Call<List<AuditoriumDto>>

    @GET("api/faculty/all")
    fun getFaculties(): Call<List<FacultyDto>>

    @GET("api/faculty/{facultyId}/group/all")
    fun getGroups(@Path("facultyId") facultyId: String): Call<List<GroupDto>>

    @GET("api/user?role=TEACHER")
    fun getTeachers(): Call<List<TeacherDto>>

    @GET("api/scheduleService")
    fun getSchedule(
        @Query("beginDate") beginDate: String?,
        @Query("endDate") endDate: String?,
        @Query("groupNumber") groupNumber: String?,
        @Query("teacherId") teacherId: String?,
        @Query("auditoryId") auditoryId: String?,
        @Query("isOnline") isOnline: Boolean?
    ): Call<List<SheduleDayDto>>


    @POST("api/user/login")
    suspend fun login(@Body loginBody: LoginBodyDto): Response<TokenDto>

    @DELETE("api/user/logout")
    suspend fun logout(@Header("Authorization") accessToken: String): Response<Unit>

    @GET("api/user/details")
    fun getUserDetails(@Header("Authorization") accessToken: String): Call<UserDto>

    @POST("api/user/refresh")
    suspend fun refresh(@Body refreshBody: RefreshBodyDto): Response<TokenDto>
}