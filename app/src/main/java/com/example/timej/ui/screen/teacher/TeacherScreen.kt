package com.example.timej.ui.screen.teacher

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.timej.R
import com.example.timej.data.dto.Auditory
import com.example.timej.data.dto.Group
import com.example.timej.data.dto.Lesson
import com.example.timej.data.dto.LessonType
import com.example.timej.data.dto.ScheduleDayDto
import com.example.timej.data.dto.Subject
import com.example.timej.data.dto.Teacher
import com.example.timej.data.net.Status
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.screen.schedule.view.LessonsDay
import com.example.timej.ui.screen.schedule.view.NoLessons
import com.example.timej.ui.screen.schedule.view.NoLessonsOnWeek
import com.example.timej.ui.view.LoadingScreen
import com.example.timej.ui.theme.*
import org.joda.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeacherScreen(
    mainViewModel: MainViewModel,
    teacherViewModel: TeacherViewModel,
    name: String
) {
    // val schedule by teacherViewModel.teacherScheduleState.observeAsState()
    val sdf = LocalDate.now()
    val mondayTest = sdf.withDayOfWeek(1)
    val schedule = listOf(
        ScheduleDayDto(
            mondayTest.toString("yyyy-MM-dd"), listOf(
                Lesson(
                    Auditory(123, "", "", "", ""),
                    "",
                    listOf(Group(972101, "", 1)),
                    id = "",
                    lessonNumber = 0,
                    lessonType = LessonType("", "Лекция"),
                    replicaId = "",
                    subject = Subject("", "Maths"),
                    teacher = Teacher("Ivan Ivanov Ivanovich", "")
                ),
                Lesson(
                    Auditory(123, "", "", "", ""),
                    "",
                    listOf(Group(972101, "", 1), Group(972102, "", 1), Group(972103, "", 1)),
                    id = "",
                    lessonNumber = 1,
                    lessonType = LessonType("", "Практическое занятие"),
                    replicaId = "",
                    subject = Subject("", "Physics"),
                    teacher = Teacher("Ivan Ivanov Ivanovich", "")
                ),
                Lesson(
                    Auditory(123, "", "", "", ""),
                    "",
                    listOf(Group(972101, "", 1)),
                    id = "",
                    lessonNumber = 2,
                    lessonType = LessonType("", "Лабораторная"),
                    replicaId = "",
                    subject = Subject("", "History"),
                    teacher = Teacher("Ivan Ivanov Ivanovich", "")
                ),
                null,
                null,
                Lesson(
                    Auditory(123, "", "", "", ""),
                    "",
                    listOf(Group(972101, "", 1)),
                    id = "",
                    lessonNumber = 5,
                    lessonType = LessonType("", "Экзамен"),
                    replicaId = "",
                    subject = Subject("", "Physical exercises"),
                    teacher = Teacher("Ivan Ivanov Ivanovich", "")
                ),
                null
            )
        )
    )

    val weekdays by teacherViewModel._weekdaysList.observeAsState()
    val monday by remember { teacherViewModel.monday }
    val saturday by remember { teacherViewModel.saturday }
    val teacherScreenState by remember { teacherViewModel.teacherScreenState }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        6
    }
    val pageCount = 6

    val currentDateAndTime = sdf.toString("dd MMMM yyyy")

    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(HawkesBlue)
                .fillMaxWidth()
                .height(129.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = name,
                    style = Shedule,
                    color = Shark
                )

                Text(
                    text = stringResource(id = R.string.date_pattern, currentDateAndTime),
                    style = SheduleDate,
                    color = Raven
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 13.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null,
                        tint = Raven,
                        modifier = Modifier
                            .size(15.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                (teacherViewModel::getPreviousSchedule)(
                                    monday
                                        .minusDays(7)
                                        .toString("yyyy-MM-dd"),
                                    saturday
                                        .minusDays(7)
                                        .toString("yyyy-MM-dd"),

                                    null,
                                    mainViewModel.teachersState.find { it.surname + " " + it.name + " " + it.middleName == name }?.id,
                                    null,
                                    null
                                )
                            }
                            .padding(2.dp)
                    )

                    repeat(pageCount) { iteration ->
                        val typeWeekdays =
                            if (pagerState.currentPage == iteration) WeekdaysEnlarged else Weekdays
                        val typeSchedule =
                            if (pagerState.currentPage == iteration) SheduleEnlarged else Shedule

                        val width by animateDpAsState(targetValue = if (pagerState.currentPage == iteration) 50.dp else 38.89.dp)
                        val height by animateDpAsState(targetValue = if (pagerState.currentPage == iteration) 50.dp else 38.89.dp)

                        Card(
                            modifier = Modifier
                                .size(width, height),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = White,
                                disabledContainerColor = White
                            )
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier =
                                Modifier
                                    .clickable(onClick = {})
                                    .fillMaxSize(),
                            ) {
                                weekdays?.get(iteration)?.let {
                                    Text(
                                        text = it.name,
                                        style = typeWeekdays,
                                        color = Raven
                                    )
                                }
                                weekdays?.get(iteration)?.let {
                                    Text(
                                        text = it.number.toString("dd"),
                                        style = typeSchedule,
                                        color = Shark
                                    )
                                }
                            }
                        }
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null,
                        tint = Raven,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                (teacherViewModel::getNextSchedule)(
                                    monday
                                        .plusDays(7)
                                        .toString("yyyy-MM-dd"),
                                    saturday
                                        .plusDays(7)
                                        .toString("yyyy-MM-dd"),

                                    null,
                                    mainViewModel.teachersState.find { it.surname + " " + it.name + " " + it.middleName == name }?.id,
                                    null,
                                    null
                                )
                            }
                            .padding(2.dp)
                    )
                }
            }
        }

        if (teacherScreenState.status == Status.LOADING) {
            LoadingScreen()
        } else if (teacherScreenState.status == Status.SUCCESS) {
            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
                pageSpacing = 0.dp,
                userScrollEnabled = true,
                reverseLayout = false,
                contentPadding = PaddingValues(0.dp),
                beyondBoundsPageCount = 0,
                pageSize = PageSize.Fill,
                key = null,
                pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                    Orientation.Horizontal
                ),
                pageContent = { page ->
                    if (schedule?.isEmpty() == true || schedule?.size == null) {
                        NoLessonsOnWeek()
                    } else {
                        val day =
                            schedule!!.find { it.date == weekdays?.get(page)?.number?.toString("yyyy-MM-dd") }
                        if (day != null) {
                            schedule!!.find { it.date == day.date }
                                ?.let { LessonsDay(lessons = it.lessons) }
                        } else {
                            NoLessons()
                        }
                    }
                }
            )
        }
    }
}