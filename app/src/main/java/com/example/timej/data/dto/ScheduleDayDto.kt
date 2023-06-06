package com.example.timej.data.dto

import com.google.gson.annotations.SerializedName

data class ScheduleDayDto(
    @SerializedName("date")
    val date: String,

    @SerializedName("lessons")
    val lessons: List<Lesson?>
)