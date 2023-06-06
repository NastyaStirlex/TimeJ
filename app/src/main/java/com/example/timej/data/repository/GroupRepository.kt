package com.example.timej.data.repository

import com.example.timej.data.dto.GroupDto
import com.example.timej.data.net.MainApiService
import javax.inject.Inject

class GroupRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    suspend fun getGroups(facultyId: String): List<GroupDto> {
        return mainApiService.getGroups(facultyId = facultyId)
    }
}