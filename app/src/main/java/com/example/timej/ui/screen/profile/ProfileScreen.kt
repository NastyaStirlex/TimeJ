package com.example.timej.ui.screen.profile

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.timej.R
import com.example.timej.data.repository.JwtRepository
import com.example.timej.data.net.Status
import com.example.timej.ui.theme.*

@Composable
fun ProfileScreen(
    onBackToClick: () -> Unit,
    profileViewModel: ProfileViewModel, context: Context,
    jwtRepository: JwtRepository, onClickLogout: () -> Unit
) {
    val name = remember { profileViewModel.name}

    val profileScreenState by remember { profileViewModel.profileScreenState }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Box(
                modifier = Modifier
                    .background(HawkesBlue)
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 22.dp)
                            .padding(horizontal = 19.dp)
                    ) {
                        IconButton(onClick = onBackToClick, modifier = Modifier.size(20.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_left),
                                contentDescription = null,
                                tint = Raven
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.menu),
                            style = SearchTitle,
                            color = Shark,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(34.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    if (name != null) {
                        Text(
                            text = name,
                            style = UserName,
                            color = Shark
                        )
                    }
                }

                Spacer(modifier = Modifier.height(33.dp))

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    ProfileRow(iconId = R.drawable.ic_heart, textId = R.string.favorites)
                    ProfileRow(
                        iconId = R.drawable.ic_groups,
                        textId = R.string.groups,
                        Modifier.padding(start = 2.dp)
                    )
                    ProfileRow(
                        iconId = R.drawable.ic_teachers,
                        textId = R.string.teachers,
                        Modifier.padding(start = 4.dp)
                    )
                    ProfileRow(iconId = R.drawable.ic_auditorium, textId = R.string.auditorium)
                    ProfileRow(iconId = R.drawable.ic_settings, textId = R.string.settings)
                }
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 26.dp, vertical = 40.dp)
                .size(107.dp, 38.dp)
                .background(Blood, RoundedCornerShape(10.dp))
                .align(Alignment.BottomStart)
                .clip(RoundedCornerShape(10.dp))
                .clickable(onClick = profileViewModel::onClickLogout)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = null,
                    tint = Bordo
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = stringResource(id = R.string.logout),
                    style = ProfileBlocks,
                    color = Bordo
                )
            }

        }
    }

    LaunchedEffect(key1 = profileScreenState.status) {
        if (profileScreenState.status == Status.SUCCESS) {
            jwtRepository.deleteAccessToken(context = context)
            jwtRepository.deleteRefreshToken(context = context)
            jwtRepository.deleteTeacherName(context)
            jwtRepository.deleteTeacherId(context)
            jwtRepository.deleteUserRole(context)
            jwtRepository.deleteGroupId(context)
            jwtRepository.deleteGroupNumber(context)
            jwtRepository.deleteStudentName(context)

            onClickLogout.invoke()
        }
    }
}

@Composable
fun ProfileRow(@DrawableRes iconId: Int, @StringRes textId: Int, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = { })
            .padding(start = 6.dp, top = 9.dp, bottom = 9.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = modifier
        )

        Spacer(modifier = Modifier.width(18.dp))

        Text(
            text = stringResource(id = textId),
            style = ProfileBlocks,
            color = Shark,
            textAlign = TextAlign.Start,
            modifier = modifier
                .weight(1f)
        )

        IconButton(onClick = { }, modifier = Modifier.size(20.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(10.dp))
    }
}