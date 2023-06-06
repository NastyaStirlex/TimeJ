package com.example.timej.data.repository

import com.example.timej.data.dto.FacultyDto
import com.example.timej.data.net.MainApiService
import javax.inject.Inject

class FacultyRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    suspend fun getFaculties(): List<FacultyDto> {
        return mainApiService.getFaculties()
    }
}