package com.example.timej.data.dto

import com.google.gson.annotations.SerializedName

data class GroupDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("groupNumber")
    val groupNumber: Int
)
