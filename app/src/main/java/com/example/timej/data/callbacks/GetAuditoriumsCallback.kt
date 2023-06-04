package com.example.timej.data.callbacks

import com.example.timej.data.dto.AuditoriumDto

interface GetAuditoriumsCallback<T> {
    fun onSuccess(
        auditoriums: List<AuditoriumDto>
    )
    fun onError(error: String?)
}