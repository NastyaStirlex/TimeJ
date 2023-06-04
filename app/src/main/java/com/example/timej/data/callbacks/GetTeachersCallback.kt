package com.example.timej.data.callbacks

import com.example.timej.data.dto.TeacherDto

interface GetTeachersCallback<T> {
    fun onSuccess(
        teachers: List<TeacherDto>
    )
    fun onError(error: String?)
}