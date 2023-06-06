package com.example.timej.data.repository

import com.example.timej.data.dto.BuildingDto
import com.example.timej.data.net.MainApiService
import javax.inject.Inject

class BuildingRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    suspend fun getBuildings(): List<BuildingDto> {
        return mainApiService.getBuildings()
    }
}