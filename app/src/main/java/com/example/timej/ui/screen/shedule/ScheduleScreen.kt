package com.example.timej.ui.screen.shedule

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.timej.*
import com.example.timej.R
import com.example.timej.data.dto.Lesson
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.screen.shedule.view.NoLessons
import com.example.timej.ui.screen.shedule.view.NoLessonsOnWeek
import com.example.timej.ui.theme.*
import org.joda.time.LocalDate
import java.util.*

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    mainViewModel: MainViewModel,
    group: String?,
    teacher: String?,
    building: String?,
    auditorium: String?
) {
    val schedule by mainViewModel.sheduleState.observeAsState()
    val weekdays by mainViewModel._weekdaysList.observeAsState()
    val monday by remember { mainViewModel.monday }
    val saturday by remember { mainViewModel.saturday }


    val sdf = LocalDate.now()
    val currentDateAndTime = sdf.toString("dd MMMM yyyy")


    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        7
    }
    val pageCount = 7

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
                    text = if (group != null) ("Group $group")
                    else teacher
                        ?: if (building != null && auditorium != null) auditorium else "",
                    style = Shedule,
                    color = Shark
                )

                Text(
                    text = "$currentDateAndTime · 7 week",
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
                                (mainViewModel::getPreviousShedule)(
                                    monday
                                        .minusDays(7)
                                        .toString("yyyy-MM-dd"),
                                    saturday
                                        .minusDays(7)
                                        .toString("yyyy-MM-dd"),

                                    if (group != null)
                                        mainViewModel.groupsState.find { it.groupNumber.toString() == group }?.id
                                    else null,
                                    if (teacher != null)
                                        mainViewModel.teachersState.find { it.surname + " " + it.name + " " + it.middleName == teacher }?.id
                                    else null,
                                    if (auditorium != null)
                                        mainViewModel.auditoriumsState.find { it.title == auditorium }?.id
                                    else null,
                                    null
                                )
                            }
                            .padding(2.dp)
                    )

                    repeat(pageCount) { iteration ->
                        val typeWeekdays =
                            if (pagerState.currentPage == iteration) WeekdaysEnlarged else Weekdays
                        val typeShedule =
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
                                        style = typeShedule,
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
                                (mainViewModel::getNextShedule)(
                                    monday
                                        .plusDays(7)
                                        .toString("yyyy-MM-dd"),
                                    saturday
                                        .plusDays(7)
                                        .toString("yyyy-MM-dd"),

                                    if (group != null)
                                        mainViewModel.groupsState.find { it.groupNumber.toString() == group }?.id
                                    else null,
                                    if (teacher != null)
                                        mainViewModel.teachersState.find { it.surname + " " + it.name + " " + it.middleName == teacher }?.id
                                    else null,
                                    if (auditorium != null)
                                        mainViewModel.auditoriumsState.find { it.title == auditorium }?.id
                                    else null,
                                    null
                                )
                            }
                            .padding(2.dp)
                    )
                }
            }
        }
        //end header's

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
                        schedule?.find { it.date == day.date }
                            ?.let { DayLessons(lessons = it.lessons) }
                    } else {
                        NoLessons()
                    }
                }
            }
        )
    }
}

data class Day(
    val number: LocalDate,
    val name: String
)

@Composable
fun LessonCard(lesson: Lesson) {

    var readyToDraw by remember { mutableStateOf(false) }
    var textStyle by remember { mutableStateOf(Subject) }

    var cardColor: Color = Color.Transparent
    var color: Color = Color.Transparent

    when (lesson.lessonType.name) {
        "Контрольная работа" -> {
            cardColor = PattensBlue
            color = BostonBlue
        }

        "Лекция" -> {
            cardColor = Pippin
            color = Roman
        }

        "Семинар" -> {
            cardColor = Karry
            color = Bamboo
        }

        "Практическое занятие" -> {
            cardColor = Solitude
            color = Indigo
        }

        "Лабораторная" -> {
            cardColor = RiceFlower
            color = Fern
        }

        "Консультация" -> {
            cardColor = PinkLace
            color = VividViolet
        }

        "Экзамен" -> {
            cardColor = Quartz
            color = BlueMarguerite
        }

        "Бронь" -> {
            cardColor = WhiteIce
            color = Gossamer
        }
    }

    Card(
        modifier = Modifier
            .size(370.dp, 135.dp)
            .padding(top = 15.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
            disabledContainerColor = cardColor,
        )
    ) {
        Box(
            modifier = Modifier
                .padding(end = 7.dp)
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 6.dp, end = 7.dp)
                    .background(color = color, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .height(20.dp)
                    .align(Alignment.TopEnd)

            ) {
                Text(
                    text = lesson.lessonType.name,
                    style = TypeLesson,
                    color = White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }

            Column(modifier = Modifier.padding(top = 10.dp, start = 15.dp)) {

                Text(
                    text = lesson.subject.name,
                    style = textStyle,
                    color = color,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier
                        .drawWithContent {
                            if (readyToDraw) drawContent()
                        }
                        .width(230.dp),
                    onTextLayout = { textLayoutResult ->
                        if (textLayoutResult.didOverflowHeight) {
                            textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                        } else {
                            readyToDraw = true
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(3.6.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.teacher),
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier
                                .size(6.62.dp, 9.4.dp)
                        )
                        Text(
                            text = lesson.teacher.fullname,
                            style = InfoLesson,
                            color = color,
                            modifier = Modifier
                                .padding(start = 1.dp)
                                .weight(1f, false),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.auditorium),
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier
                                .size(10.dp, 9.4.dp)
                        )
                        Text(
                            text = if (lesson.auditory != null) lesson.auditory.title else "",
                            style = InfoLesson,
                            color = color,
                            modifier = Modifier
                                .weight(1f, false),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.group),
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier
                                .size(9.17.dp, 9.4.dp)
                                .padding(start = 1.dp)
                        )
                        val listGroups =
                            lesson.groups.map {
                                if (it.subgroupNumber != null)
                                    it.groupNumber.toString() + "(${it.subgroupNumber})"
                                else
                                    it.groupNumber.toString()
                            }
                        Text(
                            text = listGroups.joinToString(separator = ", "),
                            style = InfoLesson,
                            color = color,
                            modifier = Modifier
                                .padding(start = 1.dp)
                                .weight(1f, false),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
            Text(
                text = when (lesson.lessonNumber + 1) {
                    1 -> "8:45 - 10:20"
                    2 -> "10:35 - 12:10"
                    3 -> "12:25 - 14:00"
                    4 -> "14:45 - 16:20"
                    5 -> "16:35 - 18:10"
                    6 -> "18:25 - 20:00"
                    7 -> "20:15 - 21:50"
                    else -> ""
                },
                style = TimeLesson,
                color = color,
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 10.dp)
                    .align(Alignment.BottomStart)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(bottom = 10.dp, end = 7.dp)
                    .size(63.dp, 12.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Text(
                    text = "${lesson.lessonNumber + 1} lesson",
                    style = NumberLesson,
                    color = color
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WindowCard(lessonNumber: Int) {
    val time = when (lessonNumber) {
        1 -> "8:45 - 10:20"
        2 -> "10:35 - 12:10"
        3 -> "12:25 - 14:00"
        4 -> "14:45 - 16:20"
        5 -> "16:35 - 18:10"
        6 -> "18:25 - 20:00"
        7 -> "20:15 - 21:50"
        else -> ""
    }
    Card(
        modifier = Modifier
            .size(370.dp, 57.dp)
            .padding(top = 15.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Whisper,
            disabledContainerColor = Whisper
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "$time · $lessonNumber lesson",
                    style = TimeWindow,
                    color = Raven
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchCard() {
    Card(
        modifier = Modifier
            .size(318.dp, 57.dp)
            .padding(top = 15.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Tara,
            disabledContainerColor = Tara
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "lunch", style = SheduleEnlarged, color = Laurel)
                Text(text = "2:00 - 2:45", style = TimeWindow, color = Laurel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreakCard() {
    Card(
        modifier = Modifier
            .size(370.dp, 39.dp)
            .padding(top = 15.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Whisper,
            disabledContainerColor = Whisper
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "15 minutes", style = TimeWindow, color = Raven)
            }
        }
    }
}


@Composable
fun DayLessons(lessons: List<Lesson?>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(top = 15.dp),
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
                        BreakCard()
                    } else if (lesson != null) {
                        LessonCard(lesson = lesson)
                    }
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
                    LunchCard()
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