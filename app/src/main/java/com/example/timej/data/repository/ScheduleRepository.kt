package com.example.timej.data.repository

import androidx.compose.runtime.MutableState
import com.example.timej.data.net.MainApiService
import com.example.timej.data.dto.ScheduleDayDto
import com.example.timej.data.net.Event
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    suspend fun getSchedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?,
        screenState: MutableState<Event<String>>
    ): List<ScheduleDayDto> {
        screenState.value = Event.loading()

        return mainApiService.getSchedule(
            beginDate = beginDate, endDate = endDate,
            groupNumber = groupNumber, teacherId = teacherId, auditoryId = auditoryId,
            isOnline = isOnline
        )
    }
}