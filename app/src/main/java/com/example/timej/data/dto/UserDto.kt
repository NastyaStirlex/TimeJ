package com.example.timej.data.dto

data class UserDto(
    val email: String,
    val gender: String,
    val group: GroupAbbreviated,
    val id: String,
    val middleName: String,
    val name: String,
    val roles: List<String>,
    val surname: String
)