package com.example.timej.data.callbacks

import com.example.timej.data.dto.BuildingDto

interface GetBuildingsCallback<T> {
    fun onSuccess(
        buildings: List<BuildingDto>
    )

    fun onError(error: String?)
}