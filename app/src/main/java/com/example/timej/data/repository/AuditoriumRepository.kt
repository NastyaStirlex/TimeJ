package com.example.timej.data.repository

import com.example.timej.data.dto.AuditoriumDto
import com.example.timej.data.net.MainApiService
import javax.inject.Inject

class AuditoriumRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    suspend fun getAuditoriums(buildingId: String): List<AuditoriumDto> {
        return mainApiService.getAuditoriums(buildingId = buildingId)
    }
}