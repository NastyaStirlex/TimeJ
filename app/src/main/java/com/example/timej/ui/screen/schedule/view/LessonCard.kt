package com.example.timej.ui.screen.schedule.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.timej.R
import com.example.timej.data.dto.Lesson
import com.example.timej.ui.theme.Bamboo
import com.example.timej.ui.theme.BlueMarguerite
import com.example.timej.ui.theme.BostonBlue
import com.example.timej.ui.theme.Fern
import com.example.timej.ui.theme.Gossamer
import com.example.timej.ui.theme.Indigo
import com.example.timej.ui.theme.InfoLesson
import com.example.timej.ui.theme.Karry
import com.example.timej.ui.theme.NumberLesson
import com.example.timej.ui.theme.PattensBlue
import com.example.timej.ui.theme.PinkLace
import com.example.timej.ui.theme.Pippin
import com.example.timej.ui.theme.Quartz
import com.example.timej.ui.theme.RiceFlower
import com.example.timej.ui.theme.Roman
import com.example.timej.ui.theme.Solitude
import com.example.timej.ui.theme.Subject
import com.example.timej.ui.theme.TimeLesson
import com.example.timej.ui.theme.TypeLesson
import com.example.timej.ui.theme.VividViolet
import com.example.timej.ui.theme.White
import com.example.timej.ui.theme.WhiteIce

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
                            text = lesson.teacher.fullName,
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