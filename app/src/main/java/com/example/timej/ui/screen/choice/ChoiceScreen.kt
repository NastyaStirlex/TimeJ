package com.example.timej.ui.screen.choice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.timej.R
import com.example.timej.ui.screen.search.view.SearchView
import com.example.timej.ui.theme.*

@Composable
fun ChoiceScreen(
    onBackClick: () -> Unit,
    onGroupsClick: () -> Unit ,
    onTeacherClick: () -> Unit,
    onAuditoriumClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp)
                    .padding(horizontal = 19.dp)
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(20.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_left),
                        contentDescription = null,
                        tint = Raven
                    )
                }

                Text(
                    text = stringResource(id = R.string.choice),
                    style = ChoiceTitle,
                    color = Shark,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                )
            }

            // GROUPS
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .background(
                        WhiteSmoke, RoundedCornerShape(
                            topStart = 70.dp,
                            topEnd = 10.dp,
                            bottomStart = 70.dp,
                            bottomEnd = 10.dp
                        )
                    )
                    .size(353.dp, 145.dp)
                    .align(Alignment.End)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(start = 7.dp)
                        .size(132.dp)
                        .clip(CircleShape)
                        .background(White)

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.groups),
                            contentDescription = null,
                            tint = Shark,
                            modifier = Modifier
                                .size(46.dp, 47.15.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.groups),
                            style = Choice,
                            color = Shark
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 44.dp)
                ) {
                    IconButton(onClick = onGroupsClick, modifier = Modifier.size(50.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_circle_right),
                            contentDescription = null,
                            tint = Shark,
                        )
                    }
                }
            }

            // TEACHER
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .background(
                        WhiteSmoke, RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 70.dp,
                            bottomStart = 10.dp,
                            bottomEnd = 70.dp
                        )
                    )
                    .size(353.dp, 145.dp)
                    .align(Alignment.Start)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 44.dp)
                ) {
                    IconButton(onClick = onTeacherClick, modifier = Modifier.size(50.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_circle_left),
                            contentDescription = null,
                            tint = Shark,
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .size(132.dp)
                        .clip(CircleShape)
                        .background(White)

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.teacher_big),
                            contentDescription = null,
                            tint = Shark,
                            modifier = Modifier
                                .size(46.dp, 47.15.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.teacher),
                            style = Choice,
                            color = Shark
                        )
                    }
                }

            }

            // AUDITORIUM
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .background(
                        WhiteSmoke, RoundedCornerShape(
                            topStart = 70.dp,
                            topEnd = 10.dp,
                            bottomStart = 70.dp,
                            bottomEnd = 10.dp
                        )
                    )
                    .size(353.dp, 145.dp)
                    .align(Alignment.End)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(start = 7.dp)
                        .size(132.dp)
                        .clip(CircleShape)
                        .background(White)

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.auditorium_big),
                            contentDescription = null,
                            tint = Shark,
                            modifier = Modifier
                                .size(46.dp, 47.15.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.auditorium),
                            style = Choice,
                            color = Shark
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 44.dp)
                ) {
                    IconButton(onClick = onAuditoriumClick, modifier = Modifier.size(50.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_circle_right),
                            contentDescription = null,
                            tint = Shark,
                        )
                    }
                }
            }
        }

    }

}