package com.example.timej.data.dto

import com.example.timej.data.net.MainApiService
import retrofit2.Call
import javax.inject.Inject

class TeacherRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    suspend fun getTeachers(): List<TeacherDto> {
        return mainApiService.getTeachers()
    }
}