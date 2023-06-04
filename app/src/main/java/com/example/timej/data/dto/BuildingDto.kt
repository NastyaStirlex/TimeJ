package com.example.timej.data.dto

import com.google.gson.annotations.SerializedName

data class BuildingDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("number")
    val number: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("address")
    val address: String
)