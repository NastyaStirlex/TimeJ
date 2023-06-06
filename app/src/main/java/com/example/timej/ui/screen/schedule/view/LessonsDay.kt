package com.example.timej.ui.screen.schedule.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timej.data.dto.Lesson

@Composable
fun LessonsDay(lessons: List<Lesson?>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(15.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(lessons) { index, lesson ->
            when (index) {
                0 -> {
                    if (lesson == null && lessons[index + 1] != null) {
                        WindowCard(1)
                    } else if (lesson != null && lessons[index + 1] != null) {
                        LessonCard(lesson = lesson)
                        BreakCard()
                    } else if (lesson != null) {
                        LessonCard(lesson = lesson)
                    }
                }

                1 -> {
                    if (lesson == null && lessons[index + 1] != null) {
                        WindowCard(2)
                    } else if (lesson != null && lessons[index + 1] != null) {
                        LessonCard(lesson = lesson)
                        BreakCard()
                    } else if (lesson != null) {
                        LessonCard(lesson = lesson)
                    }
                }

                2 -> {
                    if (lesson == null && lessons[index + 1] != null) {
                        WindowCard(3)
                    } else if (lesson != null && lessons[index + 1] != null) {
                        LessonCard(lesson = lesson)
                    } else if (lesson != null && lessons[index + 1] == null) {
                        LessonCard(lesson = lesson)
                    }
                    LunchCard()
                }

                3 -> {
                    if (lesson == null && lessons[index + 1] != null) {
                        WindowCard(4)
                    } else if (lesson != null && lessons[index + 1] != null) {
                        LessonCard(lesson = lesson)
                        BreakCard()
                    } else if (lesson != null) {
                        LessonCard(lesson = lesson)
                    }
                }

                4 -> {
                    if (lesson == null && lessons[index + 1] != null) {
                        WindowCard(5)
                    } else if (lesson != null && lessons[index + 1] != null) {
                        LessonCard(lesson = lesson)
                        BreakCard()
                    } else if (lesson != null) {
                        LessonCard(lesson = lesson)
                    }
                }

                5 -> {
                    if (lesson == null && lessons[index + 1] != null) {
                        WindowCard(6)
                    } else if (lesson != null && lessons[index + 1] != null) {
                        LessonCard(lesson = lesson)
                        BreakCard()
                    } else if (lesson != null) {
                        LessonCard(lesson = lesson)
                    }
                }

                6 -> {
                    if (lesson == null && lessons[index - 1] != null) {
                        WindowCard(7)
                    } else if (lesson != null) {
                        LessonCard(lesson = lesson)
                    }
                }
            }
        }
    }
}