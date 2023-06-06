package com.example.timej.ui.screen.schedule.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.timej.R
import com.example.timej.ui.theme.Choice
import com.example.timej.ui.theme.SearchVariant
import com.example.timej.ui.theme.Shark

@Composable
fun NoLessonsOnWeek() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_lessons),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "There are no classes this week",
            style = Choice,
            color = Shark
        )
        Text(text = "Take a break", style = SearchVariant, color = Shark)
    }
}