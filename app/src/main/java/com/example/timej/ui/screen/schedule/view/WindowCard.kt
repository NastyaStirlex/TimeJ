package com.example.timej.ui.screen.schedule.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.timej.R
import com.example.timej.ui.theme.Raven
import com.example.timej.ui.theme.TimeWindow
import com.example.timej.ui.theme.Whisper

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
                    text = stringResource(
                        id = R.string.lesson_time,
                        time,
                        lessonNumber
                    ),
                    style = TimeWindow,
                    color = Raven
                )
            }
        }
    }
}