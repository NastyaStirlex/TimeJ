package com.example.timej.data.dto

import com.google.gson.annotations.SerializedName

data class FacultyDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String
)