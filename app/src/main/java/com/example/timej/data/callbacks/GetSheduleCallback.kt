package com.example.timej.data.callbacks

import com.example.timej.data.dto.AuditoriumDto
import com.example.timej.data.dto.SheduleDayDto

interface GetSheduleCallback<T> {
    fun onSuccess(
        shedule: List<SheduleDayDto>
    )
    fun onError(error: String?)
}