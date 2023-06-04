package com.example.timej.data.callbacks

import com.example.timej.data.dto.FacultyDto

interface GetFacultiesCallback<T> {
    fun onSuccess(
        faculties: List<FacultyDto>
    )

    fun onError(error: String?)
}