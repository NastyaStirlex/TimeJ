package com.example.timej.data.callbacks

import com.example.timej.data.dto.GroupDto

interface GetGroupsCallback<T> {
    fun onSuccess(
        groups: List<GroupDto>
    )

    fun onError(error: String?)
}