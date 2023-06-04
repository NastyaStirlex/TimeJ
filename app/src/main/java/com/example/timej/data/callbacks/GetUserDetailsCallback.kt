package com.example.timej.data.callbacks

import com.example.timej.data.dto.TeacherDto
import com.example.timej.data.dto.UserDto

interface GetUserDetailsCallback<T> {
    fun onSuccess(
        userDetails: UserDto
    )
    fun onError(error: String?)
}