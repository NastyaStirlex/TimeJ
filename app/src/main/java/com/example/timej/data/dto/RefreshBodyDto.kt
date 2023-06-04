package com.example.timej.data.dto

import com.google.gson.annotations.SerializedName

data class RefreshBodyDto(
    @SerializedName("accessToken")
    val accessToken: String?,

    @SerializedName("refreshToken")
    val refreshToken: String?
)
