package com.example.timej.data.dto

import com.google.gson.annotations.SerializedName

data class LoginBodyDto(
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String


)
