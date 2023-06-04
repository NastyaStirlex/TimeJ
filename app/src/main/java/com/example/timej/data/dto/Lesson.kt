package com.example.timej.data.dto

data class Lesson(
    val auditory: Auditory?,
    val date: String,
    val groups: List<Group>,
    val id: String,
    val lessonNumber: Int,
    val lessonType: LessonType,
    val replicaId: String,
    val subject: Subject,
    val teacher: Teacher
)