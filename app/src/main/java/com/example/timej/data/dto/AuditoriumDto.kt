package com.example.timej.data.dto

import com.google.gson.annotations.SerializedName

data class AuditoriumDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("auditoryNumber")
    val auditoryNumber: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("buildingId")
    val buildingId: String
)
