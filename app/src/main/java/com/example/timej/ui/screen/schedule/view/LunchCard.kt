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
import com.example.timej.ui.theme.Laurel
import com.example.timej.ui.theme.SheduleEnlarged
import com.example.timej.ui.theme.Tara
import com.example.timej.ui.theme.TimeWindow

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
                Text(
                    text = stringResource(id = R.string.lunch),
                    style = SheduleEnlarged,
                    color = Laurel
                )
                Text(
                    text = stringResource(id = R.string.lunch_time),
                    style = TimeWindow,
                    color = Laurel
                )
            }
        }
    }
}