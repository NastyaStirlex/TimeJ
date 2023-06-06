package com.example.timej.data.net

import com.example.timej.data.dto.*
import retrofit2.Response
import retrofit2.http.*

interface MainApiService {

    @GET("api/building/all")
    suspend fun getBuildings(): List<BuildingDto>

    @GET("api/building/{buildingId}/auditory/all")
    suspend fun getAuditoriums(@Path("buildingId") buildingId: String): List<AuditoriumDto>

    @GET("api/faculty/all")
    suspend fun getFaculties(): List<FacultyDto>

    @GET("api/faculty/{facultyId}/group/all")
    suspend fun getGroups(@Path("facultyId") facultyId: String): List<GroupDto>

    @GET("api/user?role=TEACHER")
    suspend fun getTeachers(): List<TeacherDto>

    @GET("api/scheduleService")
    suspend fun getSchedule(
        @Query("beginDate") beginDate: String?,
        @Query("endDate") endDate: String?,
        @Query("groupNumber") groupNumber: String?,
        @Query("teacherId") teacherId: String?,
        @Query("auditoryId") auditoryId: String?,
        @Query("isOnline") isOnline: Boolean?
    ): List<ScheduleDayDto>


    @POST("api/user/login")
    suspend fun login(@Body loginBody: LoginBodyDto): Response<TokenDto>

    @DELETE("api/user/logout")
    suspend fun logout(@Header("Authorization") accessToken: String): Response<Unit>

    @GET("api/user/details")
    suspend fun getUserDetails(@Header("Authorization") accessToken: String): UserDto

    @POST("api/user/refresh")
    suspend fun refresh(@Body refreshBody: RefreshBodyDto): Response<TokenDto>
}