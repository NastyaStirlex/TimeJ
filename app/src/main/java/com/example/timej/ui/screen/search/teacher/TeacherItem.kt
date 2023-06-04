package com.example.timej.ui.screen.search.teacher

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.timej.R
import com.example.timej.ui.theme.Raven
import com.example.timej.ui.theme.SearchVariant
import com.example.timej.ui.theme.Shark

@Composable
fun TeacherItem(teacher: String, onItemClick: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = { onItemClick(teacher) })
            .padding(start = 6.dp, top = 10.dp, bottom = 10.dp)
    ) {

        Text(
            text = teacher,
            style = SearchVariant,
            color = Shark,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f)
        )

        IconButton(onClick = { onItemClick(teacher) }, modifier = Modifier.size(20.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(20.dp))
    }
}