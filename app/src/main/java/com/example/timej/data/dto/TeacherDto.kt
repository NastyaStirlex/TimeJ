package com.example.timej.data.dto

import com.google.gson.annotations.SerializedName

data class TeacherDto(
    @SerializedName("email")
    val email: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("group")
    val group: Any,

    @SerializedName("id")
    val id: String,

    @SerializedName("middleName")
    val middleName: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("roles")
    val roles: List<String>,

    @SerializedName("surname")
    val surname: String
)